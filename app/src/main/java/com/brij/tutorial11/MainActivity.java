package com.brij.tutorial11;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://brijabc.000webhostapp.com/android/data.json";
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendAndRequestResponse();
        recyclerView = findViewById(R.id.recycler_view); }
    private void sendAndRequestResponse() {
         //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        Log.i("Response",response);
                        //JSON CONVERSION
                        JSONObject jobject = null;
                        try {
                            jobject = new JSONObject(response);
                            JSONArray jsonArray = null;
                            jsonArray = jobject.getJSONArray("formulas");
                            ArrayList<Movie> items = new ArrayList<Movie>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jobject = jsonArray.getJSONObject(i);
                                String formula = jobject.getString("formula");
                                String url = jobject.getString("url");
                                Movie item = new Movie(formula, url);
                                items.add(item); }
                            System.out.print("AAAAAAAAAAA"+items);
                            mAdapter = new MoviesAdapter(items);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();} }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error",error.toString()); }});
        mRequestQueue.add(mStringRequest); }
}

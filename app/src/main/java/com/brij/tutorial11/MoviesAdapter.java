package com.brij.tutorial11;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private List<Movie> moviesList;
    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList; }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewItemName, textViewItemDescription;
        public MyViewHolder(View view) {
            super(view);
            textViewItemName = (TextView)
                    view.findViewById(R.id.text_view_item_name);
            textViewItemDescription = (TextView)
                    view.findViewById(R.id.text_view_item_description); }}
    @NonNull
    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);
        return new MyViewHolder(itemView); }
    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.textViewItemName.setText(movie.getItemName());
        holder.textViewItemDescription.setText(movie.getItemDescription()); }
    @Override
    public int getItemCount() {
        return moviesList.size(); }}

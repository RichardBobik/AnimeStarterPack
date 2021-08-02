package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MoviesDramaActivity extends AppCompatActivity {

    private RecyclerView moviesDramaRecView;
    private RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_drama);

        adapter = new RecViewAdapter(this);
        moviesDramaRecView = findViewById(R.id.moviesDramaRecView);

        moviesDramaRecView.setAdapter(adapter);
        moviesDramaRecView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseConnector db = new DatabaseConnector(this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        adapter.setAllShows(showSelector(allShows));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MoviesDramaActivity.this, MoviesListsActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private ArrayList<Show> showSelector(ArrayList<Show> allShows) {
        ArrayList<Show> dramaShows = new ArrayList<>();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("drama")) {
                dramaShows.add(showInstance);
            }
        }
        return dramaShows;
    }
}
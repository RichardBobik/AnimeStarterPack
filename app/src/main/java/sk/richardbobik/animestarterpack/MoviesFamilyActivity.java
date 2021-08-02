package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MoviesFamilyActivity extends AppCompatActivity {

    private RecyclerView moviesFamilyRecView;
    private RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_family);

        adapter = new RecViewAdapter(this);
        moviesFamilyRecView = findViewById(R.id.moviesFamilyRecView);

        moviesFamilyRecView.setAdapter(adapter);
        moviesFamilyRecView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseConnector db = new DatabaseConnector(this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        adapter.setAllShows(showSelector(allShows));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MoviesFamilyActivity.this, MoviesListsActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private ArrayList<Show> showSelector(ArrayList<Show> allShows) {
        ArrayList<Show> familyShows = new ArrayList<>();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("family")) {
                familyShows.add(showInstance);
            }
        }
        return familyShows;
    }
}
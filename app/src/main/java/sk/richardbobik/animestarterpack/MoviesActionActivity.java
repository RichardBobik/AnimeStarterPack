package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MoviesActionActivity extends AppCompatActivity {

    private RecyclerView moviesActionRecView;
    private RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_action);

        adapter = new RecViewAdapter(this);
        moviesActionRecView = findViewById(R.id.moviesActionRecView);

        moviesActionRecView.setAdapter(adapter);
        moviesActionRecView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseConnector db = new DatabaseConnector(this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        adapter.setAllShows(showSelector(allShows));

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MoviesActionActivity.this, MoviesListsActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private ArrayList<Show> showSelector(ArrayList<Show> allShows) {
        ArrayList<Show> actionShows = new ArrayList<>();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("action")) {
                actionShows.add(showInstance);
            }
        }
        return actionShows;
    }
}
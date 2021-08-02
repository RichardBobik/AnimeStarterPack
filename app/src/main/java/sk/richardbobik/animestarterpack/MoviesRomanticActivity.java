package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MoviesRomanticActivity extends AppCompatActivity {

    private RecyclerView moviesRomanticRecView;
    private RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_romantic);

        adapter = new RecViewAdapter(this);
        moviesRomanticRecView = findViewById(R.id.moviesRomanticRecView);

        moviesRomanticRecView.setAdapter(adapter);
        moviesRomanticRecView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseConnector db = new DatabaseConnector(this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        adapter.setAllShows(showSelector(allShows));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MoviesRomanticActivity.this, MoviesListsActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private ArrayList<Show> showSelector(ArrayList<Show> allShows) {
        ArrayList<Show> romanticShows = new ArrayList<>();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("romantic")) {
                romanticShows.add(showInstance);
            }
        }
        return romanticShows;
    }
}
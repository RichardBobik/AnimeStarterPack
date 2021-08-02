package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class SeriesFantasyActivity extends AppCompatActivity {

    private RecyclerView seriesFantasyRecView;
    private RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_fantasy);

        adapter = new RecViewAdapter(this);
        seriesFantasyRecView = findViewById(R.id.seriesFantasyRecView);

        seriesFantasyRecView.setAdapter(adapter);
        seriesFantasyRecView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseConnector db = new DatabaseConnector(this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        adapter.setAllShows(showSelector(allShows));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SeriesFantasyActivity.this, SeriesListActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private ArrayList<Show> showSelector(ArrayList<Show> allShows) {

        ArrayList<Show> fantasyShows = new ArrayList<>();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("fantasys")) {
                fantasyShows.add(showInstance);
            }
        }
        return fantasyShows;
    }
}
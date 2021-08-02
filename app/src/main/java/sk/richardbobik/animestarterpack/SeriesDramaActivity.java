package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class SeriesDramaActivity extends AppCompatActivity {

    private RecyclerView seriesDramaRecView;
    private RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_drama);

        adapter = new RecViewAdapter(this);
        seriesDramaRecView = findViewById(R.id.seriesDramaRecView);

        seriesDramaRecView.setAdapter(adapter);
        seriesDramaRecView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseConnector db = new DatabaseConnector(this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        adapter.setAllShows(showSelector(allShows));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SeriesDramaActivity.this, SeriesListActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private ArrayList<Show> showSelector(ArrayList<Show> allShows) {

        ArrayList<Show> dramaShows = new ArrayList<>();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("dramas")) {
                dramaShows.add(showInstance);
            }
        }
        return dramaShows;
    }
}
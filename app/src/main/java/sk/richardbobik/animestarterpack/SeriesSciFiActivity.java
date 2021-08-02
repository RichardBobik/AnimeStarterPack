package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class SeriesSciFiActivity extends AppCompatActivity {

    private RecyclerView seriesSciFiRecView;
    private RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_sci_fi);

        adapter = new RecViewAdapter(this);
        seriesSciFiRecView = findViewById(R.id.seriesSciFiRecView);

        seriesSciFiRecView.setAdapter(adapter);
        seriesSciFiRecView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseConnector db = new DatabaseConnector(this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        adapter.setAllShows(showSelector(allShows));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SeriesSciFiActivity.this, SeriesListActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private ArrayList<Show> showSelector(ArrayList<Show> allShows) {

        ArrayList<Show> sciFiShows = new ArrayList<>();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("scifis")) {
                sciFiShows.add(showInstance);
            }
        }
        return sciFiShows;
    }
}
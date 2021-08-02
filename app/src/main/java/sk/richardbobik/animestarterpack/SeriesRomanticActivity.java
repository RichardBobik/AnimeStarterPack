package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class SeriesRomanticActivity extends AppCompatActivity {

    private RecyclerView seriesRomanticRecView;
    private RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_romantic);

        adapter = new RecViewAdapter(this);
        seriesRomanticRecView = findViewById(R.id.seriesRomanticRecView);

        seriesRomanticRecView.setAdapter(adapter);
        seriesRomanticRecView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseConnector db = new DatabaseConnector(this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        adapter.setAllShows(showSelector(allShows));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SeriesRomanticActivity.this, SeriesListActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private ArrayList<Show> showSelector(ArrayList<Show> allShows) {

        ArrayList<Show> romanticShows = new ArrayList<>();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("romantics")) {
                romanticShows.add(showInstance);
            }
        }
        return romanticShows;
    }
}
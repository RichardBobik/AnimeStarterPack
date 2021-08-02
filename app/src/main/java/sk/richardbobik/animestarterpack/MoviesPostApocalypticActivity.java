package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MoviesPostApocalypticActivity extends AppCompatActivity {

    private RecyclerView moviesPostApocalypticRecView;
    private RecViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_post_apocalyptic);

        adapter = new RecViewAdapter(this);
        moviesPostApocalypticRecView = findViewById(R.id.moviesPostApocalypticRecView);

        moviesPostApocalypticRecView.setAdapter(adapter);
        moviesPostApocalypticRecView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseConnector db = new DatabaseConnector(this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        adapter.setAllShows(showSelector(allShows));

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MoviesPostApocalypticActivity.this, MoviesListsActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
    
    private ArrayList<Show> showSelector(ArrayList<Show> allShows) {
        ArrayList<Show> postApocalypticShows = new ArrayList<>();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("posta")) {
                postApocalypticShows.add(showInstance);
            }
        }
        return postApocalypticShows;
    }
}
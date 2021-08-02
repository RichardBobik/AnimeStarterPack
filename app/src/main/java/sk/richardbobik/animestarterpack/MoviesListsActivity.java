package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MoviesListsActivity extends AppCompatActivity {

    ImageView imgMoviesPostApocalyptic, imgMoviesAction, imgMoviesRomantic, imgMoviesFamily, imgMoviesDrama;
    ProgressBar pbMoviesPostApocalyptic, pbMoviesAction, pbMoviesRomantic, pbMoviesFamily, pbMoviesDrama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_lists);

        initViews();
        progressBarsColorChange();
        progressBarsDataSetup();

        imgMoviesPostApocalyptic.setOnClickListener(v -> {
            Intent intent = new Intent(MoviesListsActivity.this, MoviesPostApocalypticActivity.class);
            startActivity(intent);
        });

        imgMoviesAction.setOnClickListener(v -> {
            Intent intent = new Intent(MoviesListsActivity.this, MoviesActionActivity.class);
            startActivity(intent);
        });

        imgMoviesRomantic.setOnClickListener(v -> {
            Intent intent = new Intent(MoviesListsActivity.this, MoviesRomanticActivity.class);
            startActivity(intent);
        });

        imgMoviesFamily.setOnClickListener(v -> {
            Intent intent = new Intent(MoviesListsActivity.this, MoviesFamilyActivity.class);
            startActivity(intent);

        });

        imgMoviesDrama.setOnClickListener(v -> {
            Intent intent = new Intent(MoviesListsActivity.this, MoviesDramaActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MoviesListsActivity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private void initViews() {
        pbMoviesPostApocalyptic = findViewById(R.id.pbMoviesListsPostApoc);
        pbMoviesAction = findViewById(R.id.pbMoviesListsAction);
        pbMoviesRomantic = findViewById(R.id.pbMoviesListsRomantic);
        pbMoviesFamily = findViewById(R.id.pbMoviesListsFamily);
        pbMoviesDrama = findViewById(R.id.pbMoviesListsDrama);

        imgMoviesPostApocalyptic = findViewById(R.id.imgMoviesListsPostApoc);
        imgMoviesAction = findViewById(R.id.imgMoviesListsAction);
        imgMoviesRomantic = findViewById(R.id.imgMoviesListsRomantic);
        imgMoviesFamily = findViewById(R.id.imgMoviesListsFamily);
        imgMoviesDrama = findViewById(R.id.imgMoviesListsDrama);
    }

    private void progressBarsColorChange() {
        pbMoviesPostApocalyptic.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);

        pbMoviesAction.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);

        pbMoviesRomantic.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);

        pbMoviesFamily.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);

        pbMoviesDrama.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);

    }

    private void progressBarsDataSetup() {

        int postApocalypticProgressBarValue = 0;
        int actionProgressBarValue = 0;
        int romanticProgressBarValue = 0;
        int familyProgressBarValue = 0;
        int dramaProgressBarValue = 0;

        DatabaseConnector db = new DatabaseConnector(MoviesListsActivity.this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("posta") && (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked"))) {
                postApocalypticProgressBarValue = postApocalypticProgressBarValue + 20;
            }

            if (showInstance.getTag().equals("action") && (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked"))) {
                actionProgressBarValue = actionProgressBarValue + 20;
            }

            if (showInstance.getTag().equals("romantic") && (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked"))) {
                romanticProgressBarValue = romanticProgressBarValue + 20;
            }

            if (showInstance.getTag().equals("family") && (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked"))) {
                familyProgressBarValue = familyProgressBarValue + 20;
            }

            if (showInstance.getTag().equals("drama") && (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked"))) {
                dramaProgressBarValue = dramaProgressBarValue + 20;
            }
        }

        pbMoviesPostApocalyptic.setProgress(postApocalypticProgressBarValue);
        pbMoviesAction.setProgress(actionProgressBarValue);
        pbMoviesRomantic.setProgress(romanticProgressBarValue);
        pbMoviesFamily.setProgress(familyProgressBarValue);
        pbMoviesDrama.setProgress(dramaProgressBarValue);
    }
}
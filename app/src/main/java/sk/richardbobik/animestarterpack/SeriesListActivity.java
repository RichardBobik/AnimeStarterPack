package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class SeriesListActivity extends AppCompatActivity {

    ImageView imgSeriesAction, imgSeriesSciFi, imgSeriesFantasy, imgSeriesDrama, imgSeriesRomantic;
    ProgressBar pbSeriesAction, pbSeriesSciFi, pbSeriesFantasy, pbSeriesDrama, pbSeriesRomantic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);

        initViews();
        progressBarsColorsChange();
        progressBarsDataSetup();

        imgSeriesAction.setOnClickListener(v -> {
            Intent intent = new Intent(SeriesListActivity.this, SeriesActionActivity.class);
            startActivity(intent);
        });

        imgSeriesSciFi.setOnClickListener(v -> {
            Intent intent = new Intent(SeriesListActivity.this, SeriesSciFiActivity.class);
            startActivity(intent);
        });

        imgSeriesFantasy.setOnClickListener(v -> {
            Intent intent = new Intent(SeriesListActivity.this, SeriesFantasyActivity.class);
            startActivity(intent);
        });

        imgSeriesDrama.setOnClickListener(v -> {
            Intent intent = new Intent(SeriesListActivity.this, SeriesDramaActivity.class);
            startActivity(intent);
        });

        imgSeriesRomantic.setOnClickListener(v -> {
            Intent intent = new Intent(SeriesListActivity.this, SeriesRomanticActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SeriesListActivity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    private void progressBarsColorsChange() {
        pbSeriesAction.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);

        pbSeriesSciFi.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);

        pbSeriesFantasy.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);

        pbSeriesDrama.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);

        pbSeriesRomantic.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);
    }

    private void initViews() {

        imgSeriesAction = findViewById(R.id.imgSeriesListsAction);
        imgSeriesSciFi = findViewById(R.id.imgSeriesListsSciFi);
        imgSeriesFantasy = findViewById(R.id.imgSeriesListsFantasy);
        imgSeriesDrama = findViewById(R.id.imgSeriesListsDrama);
        imgSeriesRomantic= findViewById(R.id.imgSeriesListsRomantic);

        pbSeriesAction = findViewById(R.id.pbSeriesListsAction);
        pbSeriesSciFi = findViewById(R.id.pbSeriesListsSciFi);
        pbSeriesFantasy = findViewById(R.id.pbSeriesListsFantasy);
        pbSeriesDrama = findViewById(R.id.pbSeriesListsDrama);
        pbSeriesRomantic = findViewById(R.id.pbSeriesListsRomantic);
    }

    private void progressBarsDataSetup() {

        int actionProgressBarValue = 0;
        int sciFiProgressBarValue = 0;
        int fantasyProgressBarValue = 0;
        int dramaProgressBarValue = 0;
        int romanticProgressBarValue = 0;

        DatabaseConnector db = new DatabaseConnector(SeriesListActivity.this);

        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        for (Show showInstance: allShows) {
            if (showInstance.getTag().equals("actions") && (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked"))) {
                actionProgressBarValue = actionProgressBarValue + 20;
            }

            if (showInstance.getTag().equals("scifis") && (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked"))) {
                sciFiProgressBarValue = sciFiProgressBarValue + 20;
            }

            if (showInstance.getTag().equals("fantasys") && (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked"))) {
                fantasyProgressBarValue = fantasyProgressBarValue + 20;
            }

            if (showInstance.getTag().equals("dramas") && (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked"))) {
                dramaProgressBarValue = dramaProgressBarValue + 20;
            }

            if (showInstance.getTag().equals("romantics") && (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked"))) {
                romanticProgressBarValue = romanticProgressBarValue + 20;
            }
        }

        pbSeriesAction.setProgress(actionProgressBarValue);
        pbSeriesSciFi.setProgress(sciFiProgressBarValue);
        pbSeriesFantasy.setProgress(fantasyProgressBarValue);
        pbSeriesDrama.setProgress(dramaProgressBarValue);
        pbSeriesRomantic.setProgress(romanticProgressBarValue);
    }
}
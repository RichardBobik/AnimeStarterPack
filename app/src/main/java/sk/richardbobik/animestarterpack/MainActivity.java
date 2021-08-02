package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button btnIntroduction, btnSettings, btnAbout;
    ImageView btnMovies, btnSeries;
    ProgressBar pbMain;
    TextView txtProgressNumber;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new Dialog(this);

        initViews();
        progressBarDataSetup();

        btnMovies.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MoviesListsActivity.class);
            startActivity(intent);
        });

        btnSeries.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SeriesListActivity.class);
            startActivity(intent);
        });


        btnIntroduction.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, IntroductionActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    private void initViews() {

        pbMain = findViewById(R.id.pbMain);

        btnMovies = findViewById(R.id.btnMovies);
        btnSeries = findViewById(R.id.btnSeries);

        btnIntroduction = findViewById(R.id.btnIntroduction);
        btnSettings = findViewById(R.id.btnSettings);
        btnAbout = findViewById(R.id.btnAbout);

        txtProgressNumber = findViewById(R.id.txtMainPB);

    }

    private void progressNumberSetup(int progressValue) {
        txtProgressNumber.setText(progressValue + "%");
    }

    private void progressBarDataSetup() {
        int progressBarValue = 0;

        DatabaseConnector db = new DatabaseConnector(MainActivity.this);
        ArrayList<Show> allShows = (ArrayList<Show>) db.getAllData();

        for (Show showInstance : allShows) {
            if (showInstance.getViewedStatus().equals("liked") || showInstance.getViewedStatus().equals("disliked")) {
                progressBarValue = progressBarValue + 2;
            }
        }

        pbMain.setProgress(progressBarValue);
        progressNumberSetup(progressBarValue);
    }

}
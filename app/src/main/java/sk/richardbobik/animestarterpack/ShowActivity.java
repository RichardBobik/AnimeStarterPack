package sk.richardbobik.animestarterpack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class ShowActivity extends AppCompatActivity {

    public static final String SHOW_ID = "ID";
    private String status, name;

    private TextView txtActivityShowName, txtActivityShowLongDes;
    private ImageView imgActivityShowImg;
    private RadioGroup rgActivityShow;
    private RadioButton rbNotSeen, rbLiked, rbDislike, rbGeneral;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        DatabaseConnector db = new DatabaseConnector(this);
        dialog = new Dialog(this);

        initViews();

        Intent intent = getIntent();
            if (null != intent) {
                int showId = intent.getIntExtra(SHOW_ID, -1);
                if (showId != -1) {
                    Show incomingShow = db.getShowById(showId);
                    if (incomingShow != null) {
                        setData(incomingShow);
                        status = incomingShow.getViewedStatus();
                        name = incomingShow.getName();
                    }
                }
            }
        setRadioButton(rgActivityShow);
    }

    private void setData(Show show) {
        txtActivityShowName.setText(show.getName());
        txtActivityShowLongDes.setText(show.getLongDescription());
        Glide.with(this).asBitmap().load(show.getPicture()).into(imgActivityShowImg);
    }

    private void initViews() {
        txtActivityShowName = findViewById(R.id.txtActivityShowName);
        txtActivityShowLongDes = findViewById(R.id.txtActivityShowLongDes);
        imgActivityShowImg = findViewById(R.id.imgActivityShow);
        rgActivityShow = findViewById(R.id.rgActivityShow);
        rbNotSeen = findViewById(R.id.rbNotSeen);
        rbLiked = findViewById(R.id.rbLiked);
        rbDislike = findViewById(R.id.rbDisliked);

    }

    public void onRadioButtonClicked (View view) {
        int popupDecider;

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbNotSeen:
                if (checked)
                    rbNotSeen.setChecked(true);
                    status = "notViewed";
                    break;
            case R.id.rbLiked:
                if (checked)
                    rbLiked.setChecked(true);
                    status = "liked";
                    popupDecider = random();
                    if (popupDecider == 1) {
                        showPupUp();
                    }
                    break;
            case R.id.rbDisliked:
                if (checked)
                    rbDislike.setChecked(true);
                    status = "disliked";
                    popupDecider = random();
                    if (popupDecider == 1) {
                        showPupUpNegative();
                    }
                    break;
        }
        DatabaseConnector db = new DatabaseConnector(ShowActivity.this);
        db.updateOne(name, status);
        db.close();
    }

    public void setRadioButton(View view) {

        if (status.equals("notViewed")) {
            rgActivityShow.check(R.id.rbNotSeen);
        }
        else if (status.equals("liked")) {
            rgActivityShow.check(R.id.rbLiked);
        }
        else {
            rgActivityShow.check(R.id.rbDisliked);
        }
    }

    private void showPupUp() {
        int popupRoll;
        popupRoll = random();
        if (popupRoll == 1) {
            dialog.setContentView(R.layout.custom_pupup1);
        }
        else if (popupRoll == 2) {
            dialog.setContentView(R.layout.custom_popup2);
        }
        else {
            dialog.setContentView(R.layout.custop_popup3);
        }
        dialog.create();
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                t.cancel();
            }
        }, 5000);
    }

    private void showPupUpNegative() {
        int popupRoll;
        popupRoll = random();
        if (popupRoll == 1) {
            dialog.setContentView(R.layout.custom_popup4);
        }
        else if (popupRoll == 2) {
            dialog.setContentView(R.layout.custop_popup5);
        }
        else {
            dialog.setContentView(R.layout.custom_popup6);
        }
        dialog.create();
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                t.cancel();
            }
        }, 5000);
    }

    private int random() {
        int i;
        int min = 1;
        int max = 3;
        i = (int) Math.floor(Math.random()*(max-min+1)+min);
        return i;
    }

}
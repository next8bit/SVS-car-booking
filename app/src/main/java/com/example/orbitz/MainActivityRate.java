package com.example.orbitz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityRate extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView tvRateCount,tvRateMessage;
    private float ratedValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainrate);

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        tvRateCount = (TextView)findViewById(R.id.tvRateCount);
        tvRateMessage = (TextView)findViewById(R.id.tvRateMessage);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratedValue = ratingBar.getRating();
                tvRateCount.setText("Your Rating: "+ratedValue+"/5.");

                if (ratedValue < 1){
                    tvRateMessage.setText("ohh ho...");
                }else if (ratedValue < 2){
                    tvRateMessage.setText("Ok.");
                }else if (ratedValue < 3){
                    tvRateMessage.setText("Not Bad.");
                } else if (ratedValue < 4){
                    tvRateMessage.setText("Nice.");
                } else if (ratedValue < 5){
                    tvRateMessage.setText("Very Nice.");
                } else if (ratedValue == 5){
                    tvRateMessage.setText("Excellent.");
                }

                Toast.makeText(MainActivityRate.this,String.valueOf(ratingBar.getRating()),Toast.LENGTH_SHORT).show();
            }
        });

    }
}

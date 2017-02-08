package com.divyanshu.swipebutton.swipebuttonsample;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import divyanshu.ineractive.custom_view.PolygonalDrwable;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ImageView) findViewById(R.id.image))
                .setBackground(new PolygonalDrwable(Color.parseColor("#ff0000"), 15));
    }
}

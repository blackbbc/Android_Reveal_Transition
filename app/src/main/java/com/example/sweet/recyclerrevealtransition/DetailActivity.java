package com.example.sweet.recyclerrevealtransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sweet.recyclerrevealtransition.transition.RevealTransition;

import java.util.zip.Inflater;


public class DetailActivity extends Activity {

    View fullview;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = (TextView)findViewById(R.id.textView);
        Intent intent = getIntent();
        float locationX = intent.getFloatExtra("locationX", 0f);
        float locationY = intent.getFloatExtra("locationY", 0f);

        RevealTransition reveal = new RevealTransition(locationX, locationY);
        reveal.addTarget(R.id.relativelayout);

        getWindow().setEnterTransition(reveal);
        getWindow().setReturnTransition(reveal);


    }

}
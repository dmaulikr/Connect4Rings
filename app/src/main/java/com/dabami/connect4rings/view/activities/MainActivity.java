package com.dabami.connect4rings.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import dabami.connect4rings.R;


public class MainActivity extends Activity {

    private Animation animAlpha;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        setViews();
        setAnimations();
        setListeners();
    }

    private void setViews() {
        startButton = (Button) findViewById(R.id.startButton);
    }

    private void setAnimations() {
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
    }

    private void setListeners() {
        startButton.setOnClickListener(startButtonOnClickListener);
    }

    private OnClickListener startButtonOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            v.startAnimation(animAlpha);
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        }
    };
}

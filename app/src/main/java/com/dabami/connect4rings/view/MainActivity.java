package com.dabami.connect4rings.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import dabami.connect4rings.R;


public class MainActivity extends Activity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        setViews();
        setListeners();
    }

    private void setViews() {
        startButton = (Button) findViewById(R.id.startButton);
    }

    private void setListeners() {
        startButton.setOnClickListener(startButtonOnClickListener);
    }

    private OnClickListener startButtonOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        }
    };
}

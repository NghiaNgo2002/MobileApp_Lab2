package com.example.exercise4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GraphicView graphicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        graphicView = new GraphicView(this);
        setContentView(graphicView);  // Sets your custom view as the main content view
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        graphicView.releaseMediaPlayer();  // Ensures MediaPlayer is released when the activity is destroyed
    }
}

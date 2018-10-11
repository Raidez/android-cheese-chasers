package com.mga.cheesechasers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller jeu = findViewById(R.id.jeu);
        ImageView im = (ImageView) findViewById(R.id.imgNextCard);
        jeu.image = im;
    }
}

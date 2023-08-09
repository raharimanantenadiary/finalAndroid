package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // Délai de 2 secondes (2000 millisecondes)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Délai de 5 secondes avant de démarrer l'activité MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish(); // Fermer la SplashScreen pour qu'elle ne soit pas empilée dans la pile d'activités
            }
        }, SPLASH_DELAY);
    }
}

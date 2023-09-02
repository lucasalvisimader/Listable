package br.senai.sc.listable.pages.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.senai.sc.listable.R;

@SuppressWarnings("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private final static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            // Esse método será executado sempre que o timer acabar
            // E inicia a activity principal
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            // Fecha esta activity
            finish();
        }, SPLASH_TIME_OUT);
    }
}
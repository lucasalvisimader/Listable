package br.senai.sc.listable;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        TextView loginSubmit = findViewById(R.id.login_to_register_text);
        String html = "<font color=#1C1C1C>Não possui conta? </font><font color=#FD9226>Registre-se</font>";
        loginSubmit.setText(Html.fromHtml(html));
        loginSubmit.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });
    }
}

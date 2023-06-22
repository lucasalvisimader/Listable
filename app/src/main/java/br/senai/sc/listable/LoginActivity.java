package br.senai.sc.listable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        TextView loginToRegister = findViewById(R.id.login_to_register_text);
        Button loginSubmit = findViewById(R.id.login_submit);

        String html = "<font color=#1C1C1C>Não possui conta? </font><font color=#FD9226>Registre-se</font>";
        loginToRegister.setText(Html.fromHtml(html));
        onClick(loginToRegister, RegisterActivity.class);
        onClick(loginSubmit, MainActivity.class);
    }

    private void onClick(TextView from, Object to) {
        from.setOnClickListener(v -> {
            Intent i = new Intent((Context) this, (Class<?>) to);
            startActivity(i);
        });
    }
}

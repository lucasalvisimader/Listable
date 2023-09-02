package br.senai.sc.listable.pages.activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.senai.sc.listable.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView registerToLogin = findViewById(R.id.register_to_login_text);
        Button registerSubmit = findViewById(R.id.register_submit);

        String html = "<font color=#1C1C1C>Já possui conta? </font><font color=#FD9226>Ir para login</font>";
        registerToLogin.setText(Html.fromHtml(html));
        registerToLogin.setOnClickListener(v -> finish());
        registerSubmit.setOnClickListener(v -> finish());
    }
}

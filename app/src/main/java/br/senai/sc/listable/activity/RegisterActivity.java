package br.senai.sc.listable.activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.senai.sc.listable.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView registerNickname = findViewById(R.id.register_nickname);
    private TextView registerEmail = findViewById(R.id.register_email);
    private TextView registerPassword = findViewById(R.id.register_password);
    private TextView registerConfirmPassword = findViewById(R.id.register_confirm_password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        TextView registerToLogin = findViewById(R.id.register_to_login_text);
        Button registerSubmit = findViewById(R.id.register_submit);

        String html = "<font color=#1C1C1C>Já possui conta? </font><font color=#FD9226>Ir para login</font>";
        registerToLogin.setText(Html.fromHtml(html));
        registerToLogin.setOnClickListener(v -> finish());
        registerSubmit.setOnClickListener(v -> finish());
    }
}

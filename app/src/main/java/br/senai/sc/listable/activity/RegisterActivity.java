package br.senai.sc.listable.activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import br.senai.sc.listable.R;

public class RegisterActivity extends AppCompatActivity {

    private final TextView registerNickname = findViewById(R.id.register_nickname);
    private final TextView registerEmail = findViewById(R.id.register_email);
    private final TextView registerPassword = findViewById(R.id.register_password);
    private final TextView registerConfirmPassword = findViewById(R.id.register_confirm_password);
    private final Button registerSubmit = findViewById(R.id.register_submit);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        TextView registerToLogin = findViewById(R.id.register_to_login_text);

        String html = "<font color=#1C1C1C>Já possui conta? </font><font color=#FD9226>Ir para login</font>";
        registerToLogin.setText(Html.fromHtml(html));
        registerToLogin.setOnClickListener(v -> finish());

        register();
    }

    private void register() {
        registerSubmit.setOnClickListener(v -> {
            String textNickname = registerNickname.getText().toString();
            String textEmail = registerEmail.getText().toString();
            String textPassword = registerPassword.getText().toString();
            String textConfirmPassword = registerConfirmPassword.getText().toString();

            if (!textNickname.isEmpty()) {

            } else {
                Toast.makeText(RegisterActivity.this, "Preecha o apelido",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}

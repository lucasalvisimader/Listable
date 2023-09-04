package br.senai.sc.listable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.User;
import br.senai.sc.listable.utils.ConfigurationFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private User user;
    private FirebaseAuth auth;
    private final Button loginSubmit = findViewById(R.id.login_submit);
    private final EditText loginEmail = findViewById(R.id.login_email);
    private final EditText loginPassword = findViewById(R.id.login_password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        TextView loginToRegister = findViewById(R.id.login_to_register_text);

        String html = "<font color=#1C1C1C>Não possui conta? </font><font color=#FD9226>Registre-se</font>";
        loginToRegister.setText(Html.fromHtml(html));
        onClick(loginToRegister, RegisterActivity.class);

        login();
    }

    private void onClick(TextView from, Object to) {
        from.setOnClickListener(v -> {
            Intent i = new Intent(this, (Class<?>) to);
            startActivity(i);
        });
    }

    private void login() {
        loginSubmit.setOnClickListener(v -> {
            String textEmail = loginEmail.getText().toString();
            String textPassword = loginPassword.getText().toString();

            if (!textEmail.isEmpty()) {
                if (!textPassword.isEmpty()) {
                    user = new User();
                    user.setEmail(textEmail);
                    user.setPassword(textPassword);
                    validateLogin(user);
                } else {
                    Toast.makeText(LoginActivity.this, "Preecha a senha",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Preencha a email",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateLogin(User user) {
        auth = ConfigurationFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this,
                                "E-mail ou senha inválidos",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void verifyUserLogged() {
        auth = ConfigurationFirebase.getFirebaseAuth();
        if(auth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

package br.senai.sc.listable.pages.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private Button loginSubmit;
    private EditText loginEmail;
    private EditText loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        verifyUserLogged();

        loginSubmit = findViewById(R.id.login_submit);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        TextView loginEmail = findViewById(R.id.login_email);
        TextView loginPassword = findViewById(R.id.login_password);
        TextView loginToRegister = findViewById(R.id.login_to_register_text);

        String html = "<font color=#1C1C1C>Não possui conta? </font><font color=#FD9226>Registre-se</font>";
        loginToRegister.setText(Html.fromHtml(html));

        login();
        onClick(loginToRegister, RegisterActivity.class);

        loginEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });

        loginPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
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
                    showToast("Preencha a senha");
                }
            } else {
                showToast("Preencha a email");
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
                        showToast("E-mail ou senha inválidos");
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

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

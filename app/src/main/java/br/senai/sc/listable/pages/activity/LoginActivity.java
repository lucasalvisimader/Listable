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

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import br.senai.sc.listable.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        TextView loginEmail = findViewById(R.id.login_email);
        TextView loginPassword = findViewById(R.id.login_password);
        TextView loginToRegister = findViewById(R.id.login_to_register_text);
        Button loginSubmit = findViewById(R.id.login_submit);

        String html = "<font color=#1C1C1C>Não possui conta? </font><font color=#FD9226>Registre-se</font>";
        loginToRegister.setText(Html.fromHtml(html));
        onClick(loginToRegister, RegisterActivity.class);
        onClick(loginSubmit, MainActivity.class);

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

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

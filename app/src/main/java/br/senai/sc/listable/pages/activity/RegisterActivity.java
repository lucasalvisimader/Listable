package br.senai.sc.listable.pages.activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.senai.sc.listable.R;
import br.senai.sc.listable.utils.SaveUserFirebase;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private TextView registerNickname;
    private TextView registerEmail;
    private TextView registerPassword;
    private TextView registerConfirmPassword;
    private Button registerSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerNickname = findViewById(R.id.register_nickname);
        registerEmail = findViewById(R.id.register_email);
        registerPassword = findViewById(R.id.register_password);
        registerConfirmPassword = findViewById(R.id.register_confirm_password);
        registerSubmit = findViewById(R.id.register_submit);
        auth = FirebaseAuth.getInstance();
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

            if (textNickname.isEmpty()) {
                showToast("Preencha o apelido");
                return;
            }

            if (textEmail.isEmpty()) {
                showToast("Preencha o email");
                return;
            }

            if (textPassword.isEmpty()) {
                showToast("Preencha a senha");
                return;
            }

            if (!textPassword.equals(textConfirmPassword)) {
                showToast("As senhas não coincidem");
                return;
            }

            auth.createUserWithEmailAndPassword(textEmail, textPassword)
                    .addOnCompleteListener(new RegisterActivity(), task -> {
                        if (task.isSuccessful()) {
                            SaveUserFirebase.save(textNickname, textEmail);
                            finish();
                        } else {
                            showToast("Erro ao fazer o cadastro");
                        }
                    });

//            if (emailExistsInFirebase)
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

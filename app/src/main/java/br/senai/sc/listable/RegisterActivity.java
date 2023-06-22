package br.senai.sc.listable;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        TextView textView = findViewById(R.id.register_to_login_text);
        String html = "<font color=#1C1C1C>Já possui conta? </font><font color=#FD9226>Ir para login</font>";
        textView.setText(Html.fromHtml(html));
    }
}

package br.senai.sc.listable;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView textView = findViewById(R.id.login_register_text);
        String html = "<font color=#1C1C1C>Não possui conta? </font><font color=#FD9226>Registre-se</font>";
        textView.setText(Html.fromHtml(html));
    }
}
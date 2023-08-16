package br.senai.sc.listable.pages.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import br.senai.sc.listable.R;

public class AddItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items_activity);

        Toolbar toolbar = findViewById(R.id.add_items_toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        if (intent != null) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Encerrar a atividade atual
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package br.senai.sc.listable.pages.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.Item;
import br.senai.sc.listable.entity.ShoppingList;
import br.senai.sc.listable.recycleView.adapter.AdapterItems;

public class AddItemsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("fabiano0", "aaaaa0");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_activity);
        ShoppingList shoppingList;
        Intent intent = getIntent();

        Log.i("fabiano0", "aaaaa0");

        if (intent != null) {
            shoppingList = (ShoppingList) intent.getSerializableExtra("shoppingList");
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference lists = reference.child("items");

            RecyclerView recyclerView = findViewById(R.id.recicleView_add_items);
            recyclerView.setLayoutManager(new LinearLayoutManager(AddItemsActivity.this));
            recyclerView.setHasFixedSize(true);

            List<Item> itemList = new ArrayList<>();

            lists.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    itemList.clear();
                    for (DataSnapshot item : snapshot.getChildren()) {
                        Item item2 = item.getValue(Item.class);
                        if (item2 != null) {
                            itemList.add(item2);
                            Log.i("fabiano", item2.toString());
                        }
                        Log.i("fabiano2", "aaaaa");
                    }
                    Log.i("fabiano3", "aaaaa2");
                    AdapterItems adapaterItem = new AdapterItems(AddItemsActivity.this, itemList);
                    recyclerView.setAdapter(adapaterItem);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            throw new NullPointerException();
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

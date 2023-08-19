package br.senai.sc.listable.pages.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

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

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.Item;
import br.senai.sc.listable.entity.ShoppingList;
import br.senai.sc.listable.recycleView.adapter.AdapterItems;
import br.senai.sc.listable.recycleView.eventListener.RecycleItemClickListener;
import br.senai.sc.listable.utils.SaveListFirebase;

public class AddItemsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items_activity);

        ShoppingList shoppingList;
        Intent intent = getIntent();

        Toolbar toolbar = findViewById(R.id.add_items_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (intent != null) {
            shoppingList = (ShoppingList) intent.getSerializableExtra("shoppingList");
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference items = reference.child("items");

            RecyclerView recyclerView = findViewById(R.id.recicleView_add_items);
            recyclerView.setLayoutManager(new LinearLayoutManager(AddItemsActivity.this));
            recyclerView.setHasFixedSize(true);

            List<Item> itemList = new ArrayList<>();

            items.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    itemList.clear();
                    for (DataSnapshot item : snapshot.getChildren()) {
                        Item item2 = item.getValue(Item.class);
                        if (item2 != null) {
                            itemList.add(item2);
                        }
                    }
                    AdapterItems adapaterItem = new AdapterItems(AddItemsActivity.this, itemList, shoppingList);
                    recyclerView.setAdapter(adapaterItem);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            recyclerView.addOnItemTouchListener(new RecycleItemClickListener(AddItemsActivity.this,
                            recyclerView, new RecycleItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onClick(itemList.get(position), shoppingList);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Log.i("item clicado muito", position + view.toString());
                        // do whatever
                    }
                })
            );
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

    public void onClick(Item item, ShoppingList shoppingList) {
        SaveListFirebase.save(item, shoppingList);
        Intent i = new Intent(AddItemsActivity.this, ItemsActivity.class);
        i.putExtra("shoppingList", shoppingList);
        startActivity(i);
        finish();
    }
}

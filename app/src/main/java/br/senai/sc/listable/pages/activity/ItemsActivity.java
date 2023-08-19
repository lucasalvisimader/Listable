package br.senai.sc.listable.pages.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;

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
import br.senai.sc.listable.entity.ShoppingList;
import br.senai.sc.listable.recycleView.adapter.AdapterShoppingList;

public class ItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_activity);

        Button addItem = findViewById(R.id.add_item);

        String buttonText = "+ Adicionar";
        SpannableString spannableString = new SpannableString(buttonText);
        spannableString.setSpan(new AbsoluteSizeSpan(24, true), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 2, buttonText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        addItem.setText(spannableString);
        addItem.setGravity(Gravity.CENTER);

        Toolbar toolbar = findViewById(R.id.items_toolbar);
        setSupportActionBar(toolbar);

        ShoppingList shoppingList;
        Intent intent = getIntent();

        if (intent != null) {
            shoppingList = (ShoppingList) intent.getSerializableExtra("shoppingList");

            addItem.setOnClickListener(v -> onClickAddItem(shoppingList));
            Objects.requireNonNull(getSupportActionBar()).setTitle(shoppingList.getName());

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference lists = reference.child("lists").child(shoppingList.getId()).child("items");

            RecyclerView recyclerView = findViewById(R.id.recicleView_items);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            List<ShoppingList> shoppingListList = new ArrayList<>();

            lists.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    shoppingListList.clear();
                    for (DataSnapshot shoppingList : snapshot.getChildren()) {
                        ShoppingList shoppingList2 = shoppingList.getValue(ShoppingList.class);
                        if (shoppingList2 != null) {
                            shoppingListList.add(shoppingList2);
                        }
                    }
                    AdapterShoppingList adapaterItem = new AdapterShoppingList(ItemsActivity.this, shoppingListList);
                    recyclerView.setAdapter(adapaterItem);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            throw new NullPointerException();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void onClickAddItem(ShoppingList shoppingList) {
        Intent i = new Intent(ItemsActivity.this, AddItemsActivity.class);
        i.putExtra("shoppingList", shoppingList);
        startActivity(i);
    }

}

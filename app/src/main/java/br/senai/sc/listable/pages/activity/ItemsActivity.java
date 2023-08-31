package br.senai.sc.listable.pages.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import br.senai.sc.listable.recycleView.adapter.AdapterShoppingListItems;

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
            List<Item> itemList = new ArrayList<>();

            lists.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    itemList.clear();
                    for (DataSnapshot item : snapshot.getChildren()) {
                        Item item2 = item.getValue(Item.class);
                        if (item2 != null) {
                            itemList.add(item2);
                        }
                    }
                    AdapterShoppingListItems adapaterItem = new AdapterShoppingListItems(ItemsActivity.this, itemList, shoppingList);
                    recyclerView.setAdapter(adapaterItem);

                    ImageView emptyListImage = ItemsActivity.this.findViewById(R.id.items_activity_image_no_items);
                    TextView textViewTitle = ItemsActivity.this.findViewById(R.id.items_default_title);
                    TextView textViewSubtitle = ItemsActivity.this.findViewById(R.id.items_default_subtitle);
                    if (itemList.isEmpty()) {
                        emptyListImage.setVisibility(View.VISIBLE);
                        textViewTitle.setVisibility(View.VISIBLE);
                        textViewSubtitle.setVisibility(View.VISIBLE);
                    } else {
                        emptyListImage.setVisibility(View.GONE);
                        textViewTitle.setVisibility(View.GONE);
                        textViewSubtitle.setVisibility(View.GONE);
                    }
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

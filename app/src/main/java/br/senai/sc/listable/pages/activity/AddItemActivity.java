package br.senai.sc.listable.pages.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import br.senai.sc.listable.entity.ShoppingList;
import br.senai.sc.listable.recycleView.adapter.AdaaterShoppingList;

public class AddItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_activity);
        ShoppingList shoppingList;
        Intent intent = getIntent();

        if (intent != null) {
            shoppingList = (ShoppingList) intent.getSerializableExtra("shoppingList");
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference lists = reference.child("items");

            RecyclerView recyclerView = findViewById(R.id.recicleView_add_items);
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
                    AdaaterShoppingList adapaterItem = new AdaaterShoppingList(ItemsActivity.this, shoppingListList);
                    recyclerView.setAdapter(adapaterItem);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}

package br.senai.sc.listable.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import br.senai.sc.listable.R;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import br.senai.sc.listable.R;
import br.senai.sc.listable.adapter.AdapaterItem;
import br.senai.sc.listable.databinding.ActivityMainBinding;
import br.senai.sc.listable.entity.Item;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button addList = findViewById(R.id.add_list);

        String buttonText = "+ Nova lista";
        SpannableString spannableString = new SpannableString(buttonText);
        spannableString.setSpan(new AbsoluteSizeSpan(24, true), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 2, buttonText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        addList.setText(spannableString);
        addList.setGravity(Gravity.CENTER);
        addList.setOnClickListener(v -> showModal());

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setTextAppearance(this, R.style.Theme_Toolbar_Title);
                break;
            }
        }

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.setItemTextAppearance(R.style.Theme_Toolbar_Text);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_history, R.id.nav_trash)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference lists = reference.child("lists");

        RecyclerView recyclerView = findViewById(R.id.recicleView_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSizepackage br.senai.sc.listable.entity;

import java.util.Date;

import br.senai.sc.listable.R;
import br.senai.sc.listable.utils.GeneratorUUID;

        public class Item {
            private String id;
            private String name;
            private int category;
            private Integer qtd;
            private String un;
            private String price;
            private String description;
            private Date date;
            private boolean finished;

            public Item(String name) {
                this.id = GeneratorUUID.generate();
                this.name = name;
                this.category = R.drawable.default_category;
                this.finished = false;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public Integer getQtd() {
                return qtd;
            }

            public void setQtd(Integer qtd) {
                this.qtd = qtd;
            }

            public String getUn() {
                return un;
            }

            public void setUn(String un) {
                this.un = un;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Date getDate() {
                return date;
            }

            public void setDate(Date date) {
                this.date = date;
            }

            public boolean isFinished() {
                return finished;
            }

            public void setFinished(boolean finished) {
                this.finished = finished;
            }
        }
        (true);

        List<Item> itemList = new ArrayList<>();

        AdapaterItem adapaterItem = new AdapaterItem(this, itemList);
        recyclerView.setAdapter(adapaterItem);


        lists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Item item = new Item(Objects.requireNonNull(snapshot.getValue()).toString());
                itemList.add(item);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showModal() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_list_fragment);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


}

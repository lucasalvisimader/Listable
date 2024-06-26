package br.senai.sc.listable.pages.menu.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import br.senai.sc.listable.R;
import br.senai.sc.listable.databinding.FragmentHomeBinding;
import br.senai.sc.listable.entity.ShoppingList;
import br.senai.sc.listable.pages.activity.ItemsActivity;
import br.senai.sc.listable.recycleView.adapter.AdapterShoppingList;
import br.senai.sc.listable.recycleView.eventListener.RecycleItemClickListener;
import br.senai.sc.listable.utils.ConfigurationFirebase;
import br.senai.sc.listable.utils.GetUserFromFirebase;
import br.senai.sc.listable.utils.SaveListFirebase;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        Button addList = binding.getRoot().findViewById(R.id.add_list);

        String buttonText = "+ Nova lista";
        SpannableString spannableString = new SpannableString(buttonText);
        spannableString.setSpan(new AbsoluteSizeSpan(24, true), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 2, buttonText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        addList.setText(spannableString);
        addList.setGravity(Gravity.CENTER);
        addList.setOnClickListener(v -> showModal(container));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference lists = reference.child("lists");

        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.recicleView_shopping_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setHasFixedSize(true);

        List<ShoppingList> shoppingListList = new ArrayList<>();

        lists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                String idUser = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                shoppingListList.clear();
                for (DataSnapshot shoppingList : snapshot.getChildren()) {
                    ShoppingList shoppingList2 = shoppingList.getValue(ShoppingList.class);
                    if (shoppingList2 != null && shoppingList2.getIdUser().equals(idUser)) {
                        shoppingListList.add(shoppingList2);
                    }
                }
                AdapterShoppingList adapaterItem = new AdapterShoppingList(container.getContext(), shoppingListList);
                recyclerView.setAdapter(adapaterItem);

                try {
                    ImageView emptyListImage = binding.getRoot().findViewById(R.id.home_default_image_no_items);
                    TextView textViewTitle = container.findViewById(R.id.home_default_title);
                    TextView textViewSubtitle = container.findViewById(R.id.home_default_subtitle);
                    if (shoppingListList.isEmpty()) {
                        emptyListImage.setVisibility(View.VISIBLE);
                        textViewTitle.setVisibility(View.VISIBLE);
                        textViewSubtitle.setVisibility(View.VISIBLE);
                    } else {
                        emptyListImage.setVisibility(View.GONE);
                        textViewTitle.setVisibility(View.GONE);
                        textViewSubtitle.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    Log.i("fatal", Arrays.toString(e.getStackTrace()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.addOnItemTouchListener(new RecycleItemClickListener(container.getContext(),
                recyclerView, new RecycleItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    onClick(shoppingListList.get(position), container.getContext());
                }

                @Override
                public void onLongItemClick(View view, int position) {
                    Log.i("item clicado muito", position + view.toString());
                    // do whatever
                }
            })
        );

        return binding.getRoot();
    }

    private void onClick(ShoppingList shoppingList, Context context) {
        Intent i = new Intent(context, ItemsActivity.class);
        i.putExtra("shoppingList", shoppingList);
        startActivity(i);
    }

    private void showModal(ViewGroup container) {
        Dialog dialog = new Dialog(container.getContext());
        dialog.setContentView(R.layout.fragment_add_list);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button confirmButton = dialog.findViewById(R.id.add_list_create_button);
        Button cancelButton = dialog.findViewById(R.id.add_list_cancel_button);
        EditText input = dialog.findViewById(R.id.add_list_input);

        GetUserFromFirebase.get(new GetUserFromFirebase.UserCallback() {
            @Override
            public void onUserLoaded(String idUser) {
                confirmButton.setOnClickListener(view -> {
                    if (!(input.getText().toString().equals(""))) {
                        ShoppingList list = new ShoppingList();
                        list.setName(input.getText().toString());
                        list.setIdUser(idUser);
                        SaveListFirebase.save(list);
                        dialog.dismiss();
                    } else {
                        showToast(binding.getRoot(), "Nome de lista inválido");
                    }
                });
            }

            @Override
            public void onError(DatabaseError error) {
                // Tratar erros, se necessário.
            }
        });

        cancelButton.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showToast(View view, String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
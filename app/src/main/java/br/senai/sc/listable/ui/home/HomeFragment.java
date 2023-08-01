package br.senai.sc.listable.ui.home;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.listable.R;
import br.senai.sc.listable.adapter.AdapaterItem;
import br.senai.sc.listable.databinding.FragmentHomeBinding;
import br.senai.sc.listable.entity.Item;
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
        addList.setOnClickListener(v -> {
            showModal(container);
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference lists = reference.child("lists");

        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.recicleView_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setHasFixedSize(true);

        List<Item> itemList = new ArrayList<>();



        lists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemList.clear();
                for (DataSnapshot item: snapshot.getChildren()) {
                    Item item2 = item.getValue(Item.class);
                    if(item2 != null) {
                        itemList.add(item2);
                    }
                }
                AdapaterItem adapaterItem = new AdapaterItem(container.getContext(), itemList);
                recyclerView.setAdapter(adapaterItem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return binding.getRoot();
    }


    private void showModal(ViewGroup container) {
        Dialog dialog = new Dialog(container.getContext());
        dialog.setContentView(R.layout.add_list_fragment);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button confirmButton = dialog.findViewById(R.id.add_list_create_button);
        Button cancelButton = dialog.findViewById(R.id.add_list_cancel_button);
        EditText input = dialog.findViewById(R.id.add_list_input);

        confirmButton.setOnClickListener(view -> {
            SaveListFirebase.save(input.getText().toString());
            dialog.dismiss();
        });

        cancelButton.setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
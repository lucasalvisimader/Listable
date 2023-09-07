package br.senai.sc.listable.pages.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.Item;
import br.senai.sc.listable.utils.ConfigurationFirebase;

public class EditItemFragment extends DialogFragment {

    public EditItemFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_item, container, false);

        Bundle bundle = getArguments();
//        Receber id do item para deletar e editar ele
        if (bundle != null) {
            Item item = (Item) bundle.getSerializable("item");

            if (item != null) {
                TextView itemName = view.findViewById(R.id.edit_item_input_name);
                TextView itemQuantity = view.findViewById(R.id.edit_item_input_quantity);
                TextView itemUn = view.findViewById(R.id.edit_item_input_un);
                TextView itemPrice = view.findViewById(R.id.edit_item_input_price);
                TextView removeItemText = view.findViewById(R.id.edit_item_text_remove);
                ImageView removeItemImage = view.findViewById(R.id.edit_item_trash_icon);
                TextView applyEditItem = view.findViewById(R.id.edit_item_text_apply);

                String name = item.getName();
                Integer qtd = item.getQtd();
                String un = item.getUn();
                String price = item.getPrice();

                if (name != null) {
                    itemName.setText(name);
                }

                if (qtd != null) {
                    itemQuantity.setText(qtd);
                }

                if (un != null) {
                    itemUn.setText(un);
                }

                if (price != null) {
                    itemPrice.setText(price);
                }

                removeItemText.setOnClickListener(v -> {
                    DatabaseReference databaseReference = ConfigurationFirebase.getFirebase().child("lists").child("items").child(item.getId());
                    databaseReference.setValue(null);
                    Objects.requireNonNull(getDialog()).dismiss();
                });
                removeItemImage.setOnClickListener(v -> Objects.requireNonNull(getDialog()).dismiss());
                applyEditItem.setOnClickListener(v -> Objects.requireNonNull(getDialog()).dismiss());
            }
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Window window = Objects.requireNonNull(getDialog()).getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.BOTTOM);
            setCancelable(true);
        }
    }
}

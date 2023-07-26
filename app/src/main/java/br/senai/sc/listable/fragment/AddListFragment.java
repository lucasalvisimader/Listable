package br.senai.sc.listable.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.senai.sc.listable.R;
import br.senai.sc.listable.entity.ShoppingList;

public class AddListFragment extends Fragment {

    public AddListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_list_fragment, container, false);


        Button cancelButton = rootView.findViewById(R.id.add_list_cancel_button);
        Button confirmButton = rootView.findViewById(R.id.add_list_create_button);
        TextView input = rootView.findViewById(R.id.add_list_input);

        cancelButton.setOnClickListener(view -> {

        });

//        confirmButton.setOnClickListener(view -> {
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//            reference.child("listas").setValue("aaaaaaaaaa");
//        });
        confirmButton.setOnClickListener(view -> {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            DatabaseReference lists = reference.child("lists");
            ShoppingList list = new ShoppingList("socorro");

            lists.child("001").child("nome").setValue("socorro");
        });

        return rootView;
    }

}
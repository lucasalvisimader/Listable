package br.senai.sc.listable.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sc.listable.R;

public class AddListFragment extends Fragment {

    public AddListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.add_list_fragment, container, false);


//        Button cancelButton = rootView.findViewById(R.id.add_list_cancel_button);
//        Button confirmButton = rootView.findViewById(R.id.add_list_create_button);
//        TextView input = rootView.findViewById(R.id.add_list_input);
//
//        cancelButton.setOnClickListener(view -> {
//
//        });
//
//        confirmButton.setOnClickListener(view -> {
//        });

        return rootView;
    }

}
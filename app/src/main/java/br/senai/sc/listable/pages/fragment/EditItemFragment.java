package br.senai.sc.listable.pages.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import br.senai.sc.listable.R;

public class EditItemFragment extends Fragment {

    public EditItemFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_item, container, false);

//        TextView quantity = view.findViewById(R.id.edit_item_input_un);
//        int numQuantity = Integer.parseInt(quantity.getText().toString());

        return view;
    }
}

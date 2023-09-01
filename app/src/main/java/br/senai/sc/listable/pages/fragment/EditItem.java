package br.senai.sc.listable.pages.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import br.senai.sc.listable.R;

public class EditItem extends Fragment {

    public EditItem() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_item, container, false);

        EditText quantity = view.findViewById(R.id.edit_item_quantity_input);
        int numQuantity = Integer.parseInt(quantity.getText().toString());
        if (!(numQuantity >= 0)) {
            quantity.setText(numQuantity + 1);
        } else if (!(numQuantity <= 100000)){
            quantity.setText(numQuantity - 1);
        }

        return inflater.inflate(R.layout.fragment_edit_item, container, false);
    }
}

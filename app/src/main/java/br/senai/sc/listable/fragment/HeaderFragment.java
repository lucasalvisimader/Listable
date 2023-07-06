package br.senai.sc.listable.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.senai.sc.listable.R;

public class HeaderFragment extends Fragment {

    public HeaderFragment() {}

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.header_fragment, container, false);
//        System.out.println(view.toString());
//        System.out.println();
//        Button menu = view.findViewById(R.id.menuButton);
//        menu.setOnClickListener(v -> showModal(view));
//        return view;
//    }

    private void showModal(View view) {
        Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.add_list_fragment);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
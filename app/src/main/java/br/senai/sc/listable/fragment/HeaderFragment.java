package br.senai.sc.listable.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sc.listable.R;

public class HeaderFragment extends Fragment {

    public HeaderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.header_fragment, container, false);
    }
}
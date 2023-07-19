package br.senai.sc.listable.ui.terms;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import br.senai.sc.listable.R;
import br.senai.sc.listable.databinding.FragmentTermsBinding;

public class TermsFragment extends Fragment {
    private FragmentTermsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTermsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTerms;

        String termos_titulo_string = getString(R.string.termos_titulo);
        SpannableString termos_titulo = new SpannableString(termos_titulo_string);
        termos_titulo.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow_default)),
                0, termos_titulo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(termos_titulo);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

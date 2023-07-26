package br.senai.sc.listable.ui.home;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import br.senai.sc.listable.R;
import br.senai.sc.listable.databinding.FragmentHomeBinding;
import br.senai.sc.listable.fragment.AddListFragment;

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
//        addList.setOnClickListener(v -> showModal(container));
        addList.setOnClickListener(v -> {
            // Substituir o HomeFragment pelo AddListFragment
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new AddListFragment())
                    .addToBackStack(null)
                    .commit();
        });
        return binding.getRoot();
    }

//    private void showModal(ViewGroup container) {
//        Dialog dialog = new Dialog(container.getContext());
//        dialog.setContentView(R.layout.add_list_fragment);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
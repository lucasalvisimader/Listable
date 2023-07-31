package br.senai.sc.listable.ui.terms;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import br.senai.sc.listable.R;
import br.senai.sc.listable.databinding.FragmentTermsBinding;

public class TermsFragment extends Fragment {
    private FragmentTermsBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTermsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTerms;

        String termos_titulo_string = getString(R.string.termos_titulo);
        CharSequence termos_titulo_string_formatado = Html.fromHtml(termos_titulo_string, Html.FROM_HTML_MODE_LEGACY);
        SpannableString termos_titulo = new SpannableString(termos_titulo_string_formatado);
        termos_titulo.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow_default)),
                0, termos_titulo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        termos_titulo.setSpan(new AbsoluteSizeSpan(36, true),
                0, termos_titulo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        termos_titulo.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, termos_titulo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        String termos_escritos_string = getString(R.string.termos_escritos);
        CharSequence termos_escritos_string_formatado = Html.fromHtml(termos_escritos_string, Html.FROM_HTML_MODE_LEGACY);
        SpannableString termos_escritos = new SpannableString(termos_escritos_string_formatado);
        termos_escritos.setSpan(new AbsoluteSizeSpan(20, true),
                0, termos_escritos.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        String privacidade_titulo_string = getString(R.string.privacidade_titulo);
        CharSequence privacidade_titulo_string_formatado = Html.fromHtml(privacidade_titulo_string, Html.FROM_HTML_MODE_LEGACY);
        SpannableString privacidade_titulo = new SpannableString(privacidade_titulo_string_formatado);
        privacidade_titulo.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow_default)),
                0, privacidade_titulo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        privacidade_titulo.setSpan(new AbsoluteSizeSpan(36, true),
                0, privacidade_titulo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        privacidade_titulo.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, privacidade_titulo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        String email = "listable.suporte@gmail.com";
        String privacidade_escrita_string = getString(R.string.privacidade_escrita);
        CharSequence privacidade_escrita_string_formatado = Html.fromHtml(privacidade_escrita_string, Html.FROM_HTML_MODE_LEGACY);
        SpannableString privacidade_escrita = new SpannableString(privacidade_escrita_string_formatado);
        privacidade_escrita.setSpan(new AbsoluteSizeSpan(20, true),
                0, privacidade_escrita.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        privacidade_escrita.setSpan(new android.text.style.URLSpan("mailto:" + email),
                (privacidade_escrita.length() - 30), (privacidade_escrita.length() - 4), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        privacidade_escrita.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow_default)),
                (privacidade_escrita.length() - 30), (privacidade_escrita.length() - 4), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString todos_termos_privacidade = new SpannableString(
                TextUtils.concat(termos_titulo, termos_escritos, privacidade_titulo, privacidade_escrita));

        textView.setMovementMethod(LinkMovementMethod.getInstance());

        textView.setText(todos_termos_privacidade);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

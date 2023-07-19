package br.senai.sc.listable.ui.terms;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.senai.sc.listable.R;

public class TermsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private final Context mContext;

    public TermsViewModel(Context context) {
        this.mContext = context;
        mText = new MutableLiveData<>();
        mText.setValue(context.getString(R.string.termos_privacidade_escritos));
    }

    public LiveData<String> getText() {
        return mText;
    }
}

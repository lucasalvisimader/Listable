package br.senai.sc.listable.ui.terms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TermsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public TermsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is terms fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

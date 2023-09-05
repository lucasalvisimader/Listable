package br.senai.sc.listable.pages.menu.config;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfigViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ConfigViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is config fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

package com.example.v2.ui.mandala;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MandalaViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MandalaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Mandala fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

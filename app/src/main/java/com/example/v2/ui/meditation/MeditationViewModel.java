package com.example.v2.ui.meditation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MeditationViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MeditationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Meditation fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

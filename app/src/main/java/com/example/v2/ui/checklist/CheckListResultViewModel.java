package com.example.v2.ui.checklist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CheckListResultViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CheckListResultViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Checklist Result fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

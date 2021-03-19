package com.example.v2.ui.meditation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.v2.R;

public class MeditationFragment extends Fragment {
    private MeditationViewModel meditationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        meditationViewModel = ViewModelProviders.of(this).get(MeditationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_meditation, container, false);
        final TextView textView = root.findViewById(R.id.text_meditation);
        meditationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}

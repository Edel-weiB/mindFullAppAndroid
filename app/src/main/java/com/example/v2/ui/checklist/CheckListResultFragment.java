package com.example.v2.ui.checklist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Set;

public class CheckListResultFragment extends Fragment {
    private CheckListResultViewModel checkListResultViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        checkListResultViewModel = ViewModelProviders.of(this).get(CheckListResultViewModel.class);
        View root = inflater.inflate(R.layout.fragment_checklist_result, container, false);
        final TextView textView = root.findViewById(R.id.text_checklist_result);
        checkListResultViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Get checklist result user data
        Context context = root.getContext();

        SharedPreferences sharedPreferences = context.getSharedPreferences("checklist_result", context.MODE_PRIVATE);
        String array = sharedPreferences.getString("USER_DATA", null);

        Integer sum_checklist = 0;
        for (int i = 0; i < array.length(); i++) {
            sum_checklist = sum_checklist + (array.charAt(i) - '0');
        }

        // Checlist Result Value
        TextView checkList_result_text_view = root.findViewById(R.id.result_score);
        checkList_result_text_view.setText(String.valueOf(sum_checklist));

        return root;
    }
}

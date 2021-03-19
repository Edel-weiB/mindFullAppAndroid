package com.example.v2.ui.checklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.v2.R;

public class ChecklistFragment extends Fragment implements View.OnClickListener {
    private ChecklistViewModel checklistViewModel;
    QuestionStatus questionStatus = new QuestionStatus();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        checklistViewModel = ViewModelProviders.of(this).get(ChecklistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_checklist, container, false);
        final TextView textView = root.findViewById(R.id.text_checklist);
        checklistViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Question status
        questionStatus.set_selected_text((TextView) root.findViewById(R.id.checklist_question_status));
        questionStatus.set_text();

        // Question
        questionStatus.setTarget_text((TextView) root.findViewById(R.id.checklist_text_question));
        questionStatus.set_question();

        // SeekBar
        questionStatus.setSeekBar((SeekBar) root.findViewById(R.id.seek_bar));

        // Root View
        questionStatus.setRoot_view(root);

        // Button
        Button button_back = root.findViewById(R.id.button_back);
        Button button_next = root.findViewById(R.id.button_next);
        button_back.setOnClickListener(this);
        button_next.setOnClickListener(this);

        return root;


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_back:
                questionStatus.back_question();
                questionStatus.set_text();
                questionStatus.set_question();
                break;
            case R.id.button_next:
                questionStatus.next_question();
                questionStatus.set_text();
                questionStatus.set_question();
                break;
        }
    }
}
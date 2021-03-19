package com.example.v2.ui.checklist;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.v2.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class QuestionStatus {
    Integer question_no;
    Integer max_question;
    TextView selected_text;
    TextView target_text;
    SeekBar seekBar;
    View root_view;

    String[] question_list = {
            "I have been feeling cheerful",
            "I feel very lonely even if I am with my friends",
            "I enjoy learning new things",
            "When I work or at school, I feel emotionally drained",
            "I can tolerate the pressure of my work very well",
            "Generally I feel very confident about myself",
            "I feel that I cannot handle many things at one time"
    };

    Integer[] user_data;

    public QuestionStatus () {
        this.question_no = 1;
        this.max_question = 7;
        user_data = new Integer[this.max_question];
    }

    public void set_selected_text(TextView textView) {
        this.selected_text = textView;
    }

    public void setTarget_text(TextView target_text) {
        this.target_text = target_text;
    }

    public void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    public void setRoot_view(View root_view) {
        this.root_view = root_view;
    }

    private void store_input() {
        this.user_data[this.question_no-1] = this.seekBar.getProgress();
    }

    private void load_input() {
        this.seekBar.setProgress(this.user_data[this.question_no-1]);
    }

    private void set_middle() {
        this.seekBar.setProgress(2);
    }

    public void next_question() {
        if (this.question_no == this.max_question) {
            this.store_input();
            this.submit();
        } else if (this.question_no < this.max_question) {
            this.store_input();
            this.question_no = this.question_no + 1;
            this.set_middle();
        }
    }

    public void back_question() {
        if (this.question_no > 1) {
            this.question_no = this.question_no - 1;
            this.load_input();
        }
    }

    private String get_text() {
        return "Question " + String.valueOf(this.question_no) + " out of " + String.valueOf(this.max_question);
    }

    public void set_text() {
        this.selected_text.setText(this.get_text());
    }

    public void set_question() {
        this.target_text.setText(question_list[question_no-1]);
    }

    private String convert_2_string() {
        String array = "";

        for (Integer i: this.user_data) {
            array = array + String.valueOf(i);
        }

        return array;
    }

    public void submit() {
        Context context = this.root_view.getContext();

        // Shared Preferences
        SharedPreferences.Editor editor = context.getSharedPreferences("checklist_result", context.MODE_PRIVATE).edit();
        editor.clear();
        editor.putString("USER_DATA", convert_2_string());
        editor.apply();

        // Nagivate
        NavController navController = Navigation.findNavController(this.root_view);
        navController.navigate(R.id.nav_checklist_result);
    }
}

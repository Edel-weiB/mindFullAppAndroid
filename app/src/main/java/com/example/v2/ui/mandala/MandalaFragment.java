package com.example.v2.ui.mandala;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.v2.R;

public class MandalaFragment extends Fragment implements View.OnClickListener {
    private MandalaViewModel mandalaViewModel;
    private View currentRoot;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mandalaViewModel = ViewModelProviders.of(this).get(MandalaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mandala, container, false);
        final TextView textView = root.findViewById(R.id.text_mandala);
        mandalaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        currentRoot = root;


        Button start_mandala = root.findViewById(R.id.start_madala);
        start_mandala.setOnClickListener(this);



        return root;
    }

    @Override
    public void onClick(View view) {
        NavController navController = Navigation.findNavController(currentRoot);
        navController.navigate(R.id.nav_mandala_draw_page);
    }
}

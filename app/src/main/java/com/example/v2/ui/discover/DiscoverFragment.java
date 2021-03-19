package com.example.v2.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.v2.R;
import com.example.v2.ui.fav.FavViewModel;

public class DiscoverFragment extends Fragment implements View.OnClickListener {
    private DiscoverViewModel discoverViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        discoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
        View root = inflater.inflate(R.layout.fragment_discover, container, false);


        // Button on Click Listener
        LinearLayout button_meditation = root.findViewById(R.id.button_meditation);
        LinearLayout button_mandala = root.findViewById(R.id.button_mandala);
        LinearLayout button_podcast = root.findViewById(R.id.button_podcast);
        button_meditation.setOnClickListener(this);
        button_mandala.setOnClickListener(this);
        button_podcast.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
//        Activity activity = new MainActivity();
//        NavController navController = Navigation.findNavController(new MainActivity(), R.id.nav_host_fragment);
        NavController navController = Navigation.findNavController(getView());
        switch (view.getId()) {
            case R.id.button_meditation:
                navController.navigate(R.id.nav_meditation);
                break;
            case R.id.button_mandala:
                navController.navigate(R.id.nav_mandala);
                break;
            case R.id.button_podcast:
                navController.navigate(R.id.nav_podcast);
                break;
        }
    }
}

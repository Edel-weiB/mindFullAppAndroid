package com.example.v2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.v2.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        // Button on Click Listener
        LinearLayout profile_button = root.findViewById(R.id.button_profile);
        LinearLayout blog_button = root.findViewById(R.id.button_blog);
        LinearLayout checklist_button = root.findViewById(R.id.button_checklist);
        profile_button.setOnClickListener(this);
        blog_button.setOnClickListener(this);
        checklist_button.setOnClickListener(this);

        // Uncheck Bottom
//        BottomNavigationView bottomNavigationView = new MainActivity().bottomNavigationView;
//        BottomNavigationView bottomNavigationView = root.findViewById(R.id.bottom_nav);
//        int size = bottomNavigationView.getMenu().size();
//        for (int i = 0; i < size; i++) {
//            bottomNavigationView.getMenu().getItem(i).setChecked(false);
//        }

        return root;
    }

    @Override
    public void onClick(View view) {
//        Activity activity = new MainActivity();
//        NavController navController = Navigation.findNavController(new MainActivity(), R.id.nav_host_fragment);
        NavController navController = Navigation.findNavController(getView());
        switch (view.getId()) {
            case R.id.button_profile:
                navController.navigate(R.id.nav_profile);
                break;
            case R.id.button_blog:
                navController.navigate(R.id.nav_blog);
                break;
            case R.id.button_checklist:
                navController.navigate(R.id.nav_checklist);
                break;
        }
    }
}
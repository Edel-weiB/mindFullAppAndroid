package com.example.v2.ui.podcast;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2.R;

import java.util.ArrayList;

public class PodcastFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private ArrayList<Music> musicArrayList;

    private MediaPlayerSelector mediaPlayerSelector;
    private Button play_pause_button;
    private TextView music_title;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_podcast, container, false);

        Context context = root.getContext();
        music_title = root.findViewById(R.id.music_title);

        // Image
        ImageGallery imageGallery = new ImageGallery((ImageView) root.findViewById(R.id.imageGallery), context);

        // Recycler View
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(context));
        musicArrayList = new ArrayList<>();
        musicAdapter = new MusicAdapter(context, musicArrayList);
        recyclerView.setAdapter(musicAdapter);
        createListData();

        // Mediaplayer
//        mediaPlayer = MediaPlayer.create(context, R.raw.podcast1);
//        mediaPlayer.start();
//        mediaPlayer.pause();
        mediaPlayerSelector = new MediaPlayerSelector(context);

        // Button
        play_pause_button = root.findViewById(R.id.play_pause_button);
        play_pause_button.setOnClickListener(this);

        // Wait untill view is ready then apply onclick listenser
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    LinearLayout linearLayout = (LinearLayout) recyclerView.getChildAt(i);
                    final TextView textView = (TextView) linearLayout.getChildAt(0);
                    final String file_name = (String) textView.getTag();

                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mediaPlayerSelector.initialised) {
                                mediaPlayerSelector.destropMediaPlater();
                            }


                            mediaPlayerSelector.setFileLocation(file_name);
                            mediaPlayerSelector.initialiseMediaPlayer();

                            music_title.setText(textView.getText());
                            play_pause_button.setText("Play");
                        }
                    });
                }
            }
        });




//        music_rows_main = root.findViewWithTag("music_rows");
//        music_rows_main.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TextView tempTextView = (TextView) ((LinearLayout) view).getChildAt(0);
//                String title = (String) tempTextView.getText();
//                Log.d("ON click", "onClick: " + title);
//            }
//        });

        return root;
    }

    private void createListData() {
        Music podcast1 = new Music("podcast1", "Podcast 1", 100);
        Music rainforest = new Music("rainforest", "Rainforest", 113);
        Music meditation1 = new Music("meditation1", "Meditation 1", 105);
        Music natureSound1 = new Music("naturesound1", "Nature Sound 1", 143);
        musicArrayList.add(podcast1);
        musicArrayList.add(rainforest);
        musicArrayList.add(meditation1);
        musicArrayList.add(natureSound1);
        musicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        mediaPlayerSelector.destropMediaPlater();
    }

    @Override
    public void onClick(View view) {
        if (play_pause_button.getText() == "Play") {
            play_pause_button.setText("Pause");
            mediaPlayerSelector.start();
        } else {
            play_pause_button.setText("Play");
            mediaPlayerSelector.pause();
        }
    }
}

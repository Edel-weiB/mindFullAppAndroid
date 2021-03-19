package com.example.v2.ui.podcast;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2.R;

public class MusicHolder extends RecyclerView.ViewHolder {
    private TextView music_duration, title;

    public MusicHolder(View itemView) {
        super(itemView);
        this.music_duration = itemView.findViewById(R.id.music_duration);
        this.title = itemView.findViewById(R.id.title);
    }

    public void setMusicDetails(Music music) {
        this.music_duration.setText(String.valueOf(music.getDuration_total_sec()));
        this.title.setText(music.getTitle());
        this.title.setTag(music.getFile_location());
    }
}

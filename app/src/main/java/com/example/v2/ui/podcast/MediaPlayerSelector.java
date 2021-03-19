package com.example.v2.ui.podcast;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.v2.R;

public class MediaPlayerSelector {
    private MediaPlayer mediaPlayer;
    private String fileLocation;
    private Context context;
    public boolean initialised;

    public MediaPlayerSelector(Context context) {
        this.context = context;
        this.initialised = false;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public void initialiseMediaPlayer() {
        int resource_name = context.getResources().getIdentifier(this.fileLocation, "raw", context.getPackageName());
        this.mediaPlayer = MediaPlayer.create(this.context, resource_name);
        this.initialised = true;
    }

    public void destropMediaPlater() {
        this.mediaPlayer.release();
        this.mediaPlayer = null;
    }

    public void pause() {
        this.mediaPlayer.pause();
    }

    public void start() {
        this.mediaPlayer.start();
    }
}

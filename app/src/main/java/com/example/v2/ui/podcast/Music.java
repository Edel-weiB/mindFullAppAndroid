package com.example.v2.ui.podcast;

public class Music {
    private Integer duration_total_sec;
    private String title;
    private String file_location;

    public Music(String file_name, String title, Integer duration_total_sec) {
        this.file_location = file_name;
        this.title = title;
        this.duration_total_sec = duration_total_sec;
    }

    public Integer getDuration_total_sec() {
        return duration_total_sec;
    }

    public String getTitle() {
        return title;
    }

    public String getFile_location() {
        return file_location;
    }
}

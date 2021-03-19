package com.example.v2.ui.mandala;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.v2.R;

public class MandalaDrawPage extends AppCompatActivity implements View.OnClickListener {

    private DrawView drawView, presetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandala_draw_page);

        // Drawing Canvas
        drawView = findViewById(R.id.drawing_canvas);
        drawView.setVisibility(View.VISIBLE);
        drawView.setEnabled(true);
        drawView.invalidate();

        // Preset Canvas
        presetView = findViewById(R.id.preset_canvas);
        presetView.setVisibility(View.VISIBLE);
        presetView.setEnabled(false);
        presetView.invalidate();


        // Button
        findViewById(R.id.button_red).setOnClickListener(this);
        findViewById(R.id.button_orange).setOnClickListener(this);
        findViewById(R.id.button_yellow).setOnClickListener(this);
        findViewById(R.id.button_green).setOnClickListener(this);

        findViewById(R.id.button_erase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setDrawPaintColor(Color.TRANSPARENT);
                drawView.setErase(true);
            }
        });

        findViewById(R.id.set_layout_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.drawPresetMandala(2);
            }
        });

        // Thickness
        ((SeekBar) findViewById(R.id.brush_thickness)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                drawView.setPaintAlpha(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        drawView.setErase(false);

        int choosen_colour = ((ColorDrawable) view.getBackground()).getColor();
        drawView.setDrawPaintColor(choosen_colour);
    }
}
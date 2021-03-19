package com.example.v2.ui.mandala;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.example.v2.R;

public class DrawView extends View {

    static Path drawPath;
    private Paint drawPaint, canvasPaint, presetDrawing;
    private Canvas drawCanvas, presetCanvas;
    private Bitmap canvasBitmap, presetBitmap;

    // Paint settings
    static int paintColor = 0xFFFF0000;
    private  float STROKE_WIDTH = 5f;
    private boolean erase=false;

    // Size
    private int w;
    private int h;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
        setErase(erase);

    }

    private void setupDrawing(){
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(STROKE_WIDTH);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void setErase(boolean isErase){
        erase=isErase;
        drawPaint = new Paint();
        if(erase) {
            setupDrawing();
            int srcColor= 0x00000000;

            PorterDuff.Mode mode = PorterDuff.Mode.CLEAR;
            PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(srcColor, mode);

            drawPaint.setColorFilter(porterDuffColorFilter);

            drawPaint.setColor(srcColor);
            drawPaint.setXfermode(new PorterDuffXfermode(mode));
        }
        else {
            setupDrawing();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);

        presetBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        presetCanvas = new Canvas(presetBitmap);

        this.h = h;
        this.w = w;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        canvasPaint.setColor(paintColor);
        float touchX = event.getX();
        float touchY = event.getY();
        //respond to down, move and up events

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX, touchY);
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        //redraw
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawBitmap(presetBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    public void setDrawPaintColor(int paintColour) {
        this.drawPaint.setColor(paintColour);
    }

    public int getPaintAlpha(){
        return Math.round((float)STROKE_WIDTH/255*100);
    }

    public void setPaintAlpha(int newAlpha){
        STROKE_WIDTH=Math.round((float)newAlpha/100*255);
        drawPaint.setStrokeWidth(newAlpha);
    }


    public void drawPresetMandala(int n) {
        presetDrawing = new Paint();
        presetDrawing.setAntiAlias(true);
        presetDrawing.setColor(0x559E9E9E);
        presetDrawing.setStrokeWidth(5);
        presetDrawing.setStyle(Paint.Style.STROKE);
        switch (n) {
            case 1:
                int margin = 20;
                int centre_w = w/2;
                int centre_h = h/2;
                presetCanvas.drawCircle(centre_w,centre_h,(centre_w-margin),presetDrawing);
                break;

            case 2:
                Drawable drawable = getResources().getDrawable(R.drawable.mandala, null);
                drawable.setBounds(0, 0, w, h);
                drawable.setAlpha(50);
                drawable.draw(presetCanvas);
                break;

            default:

        }
    }
}

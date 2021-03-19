package com.example.v2.ui.podcast;

import android.content.Context;
import android.widget.ImageView;

import com.example.v2.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageGallery {

    /*
    TODO Next Image
    TODO Previous Image
    TODO Load Image
    TODO Show Image
    TODO Hide Image
    TODO AutoScroll image
     */


    private ImageView imageViewGallery;
    private Context context;

    private List<Integer> imageGallery = new ArrayList<Integer>();
    private Random random = new Random();

    public ImageGallery(ImageView imageViewGallery, Context context) {
        this.imageViewGallery = imageViewGallery;
        this.context = context;

        this.loadImage();
    }

    private void loadImage() {
        imageGallery.add(R.drawable.maxim_vengerov);
        imageGallery.add(R.drawable.hilary_hahn);
    }

    private void setImage() {
        Integer choosenImage = random.nextInt(imageGallery.size());
        imageViewGallery.setImageResource(imageGallery.get(choosenImage));
    }
}

package com.mobarokOP.imageslider;

import androidx.annotation.Nullable;

public class SlideModel {
    String imageLink;
    int imageDrawable;
    String title;
    String description;
    String openLink;



    public SlideModel(String imageLink) {
        this.imageLink = imageLink;
        this.title = null;
        this.description = null;
        this.openLink = null;
    }

    public SlideModel(int imageDrawable) {
        this.imageDrawable = imageDrawable;
        this.title = null;
        this.description = null;
        this.openLink = null;
    }


    public SlideModel(String imageLink,@Nullable String openLink) {
        this.imageLink = imageLink;
        this.title = null;
        this.description = null;
        this.openLink = openLink;
    }

    public SlideModel(int imageDrawable, @Nullable String openLink) {
        this.imageDrawable = imageDrawable;
        this.title = null;
        this.description = null;
        this.openLink = openLink;
    }



    public SlideModel(String imageLink, String title, String description,@Nullable String openLink) {
        this.imageLink = imageLink;
        this.title = title;
        this.description = description;
        this.openLink = openLink;
    }

    public SlideModel(int imageDrawable, String title, String description, @Nullable String openLink) {
        this.imageDrawable = imageDrawable;
        this.title = title;
        this.description = description;
        this.openLink = openLink;
    }



    public SlideModel(String imageLink, String title, String description) {
        this.imageLink = imageLink;
        this.title = title;
        this.description = description;
        this.openLink = null;
    }

    public SlideModel(int imageDrawable, String title, String description) {
        this.imageDrawable = imageDrawable;
        this.title = title;
        this.description = description;
        this.openLink = null;
    }

    public String getImageLink() {
        return imageLink;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOpenLink() {
        return openLink;
    }
}

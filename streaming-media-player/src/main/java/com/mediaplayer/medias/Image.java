package com.mediaplayer.medias;

public class Image {

    private String fileName;

    public Image (String fileName) {
        this.fileName = fileName;
    }

    public void diaplay() {
        System.out.println("Diaplaying Image " + this.fileName);
    }

}

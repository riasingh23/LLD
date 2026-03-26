package com.mediaplayer.medias;

public class Video implements Media{

    private String fileName;

    public Video (String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void play() {
        System.out.println("Playing Video " + this.fileName);
    }

}

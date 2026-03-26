package com.mediaplayer.utility;

import java.util.ArrayList;
import java.util.List;

import com.mediaplayer.medias.Media;

public class MediaCollection implements Media {

    List<Media> mediaList = new ArrayList<>();

    public MediaCollection(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    @Override
    public void play() {
        System.out.println("Playing all media from collection");
        for (Media media : this.mediaList) {
            media.play();
        }
    }

    public void addMedia(Media media) {
        this.mediaList.add(media);
    }

    public void removeMedia(Media media) {
        this.mediaList.remove(media);
    }

}

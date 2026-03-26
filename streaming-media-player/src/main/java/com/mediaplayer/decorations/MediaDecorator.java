package com.mediaplayer.decorations;

import com.mediaplayer.medias.Media;

public abstract class MediaDecorator implements Media {

    private Media decoratedMedia;

    MediaDecorator(Media decoratedMedia) {
        this.decoratedMedia = decoratedMedia;
    }

    public void play() {
        this.decoratedMedia.play();
    }

}

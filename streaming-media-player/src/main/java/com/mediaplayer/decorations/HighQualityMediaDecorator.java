package com.mediaplayer.decorations;

import com.mediaplayer.medias.Media;

public class HighQualityMediaDecorator extends MediaDecorator {

    public HighQualityMediaDecorator(Media decoratedMedia) {
        super(decoratedMedia);
    }

    public void play() {
        super.play();
        System.out.println("Enhancing Playback Quality");
    }

}
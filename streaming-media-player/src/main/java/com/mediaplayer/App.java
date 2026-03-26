package com.mediaplayer;

import java.util.ArrayList;
import java.util.List;

import com.mediaplayer.decorations.HighQualityMediaDecorator;
import com.mediaplayer.medias.Audio;
import com.mediaplayer.medias.Image;
import com.mediaplayer.medias.ImageAdapter;
import com.mediaplayer.medias.Media;
import com.mediaplayer.medias.Video;
import com.mediaplayer.utility.MediaCollection;

public class App {
    public static void main(String[] args) {
        Media audio = new Audio("song.mp3");
        Media video = new Video("movie.mp4");
        Image image = new Image("picture.jpg");
        ImageAdapter imageAdapter = new ImageAdapter(image);

        Media highQualityMediaDecorator = new HighQualityMediaDecorator(audio);

        List<Media> mediaList = new ArrayList<>();
        mediaList.add(audio);
        mediaList.add(video);
        mediaList.add(imageAdapter);

        MediaCollection mediaCollection = new MediaCollection(mediaList);
        mediaCollection.addMedia(highQualityMediaDecorator);
        mediaCollection.play();

    }
}

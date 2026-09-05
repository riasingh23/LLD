package dj.service;

import java.util.List;

public class RecommendationService {
    List<Song> songList;
    public List<Song> getSongList() {
        return songList;
    }
    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
}

package dj.service;

import java.util.List;

public interface MixingStrategy {
    public List<Song> mixPlaylist(List<Song> recommendationSongs, List<Song> DjSongs);
}

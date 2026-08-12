package dj.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlaylistMix {
    private DjService djService;
    private RecommendationService  recommendationService;
    private Queue<Song> playlist = new LinkedList<>();
    private List<Song> alreadyPlayedSongs = new ArrayList<>();
    private MixingStrategy mixingStrategy;

    public PlaylistMix(DjService djService, RecommendationService recommendationService, MixingStrategy mixingStrategy) {
        this.djService = djService;
        this.recommendationService = recommendationService;
        this.mixingStrategy = mixingStrategy;
    }

    public Song getNextSong() {
        Song nextSong = playlist.peek();
        playlist.poll();
        alreadyPlayedSongs.add(nextSong);
        return nextSong;
    }

    public void mixPlaylist(UserPreference userPreference) {
        removePlayedSong();
        removeNotPreferredSong(userPreference, djService.getSongList());
        removeNotPreferredSong(userPreference, recommendationService.getSongList());
        List<Song> songList = mixingStrategy.mixPlaylist(djService.getSongList(), recommendationService.getSongList());
        playlist.addAll(songList);
    }

    private void removeNotPreferredSong(UserPreference userPreference, List<Song> songList) {
        songList.removeIf(song ->
                song.getDuration() > userPreference.getMaxDuration()
                        || !userPreference.getPreferredGenres().contains(song.getGenre())
                        || !userPreference.getPreferredSingers().contains(song.getArtist())
        );
    }

    private void removePlayedSong() {
        alreadyPlayedSongs.removeIf(song -> !playlist.contains(song));
    }

}

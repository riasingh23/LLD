package dj.service;

public class Song {
    private int id;
    private String name;
    private String singer;
    private String album;
    private String artist;
    private int duration;
    private String genre;

    private Song(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public static class SongBuilder {
        Song song = new Song();
        public SongBuilder id(int id) {
            this.song.id = id;
            return this;
        }
        public SongBuilder name(String name) {
            this.song.name = name;
            return this;
        }
        public SongBuilder singer(String singer) {
            this.song.singer = singer;
            return this;
        }
        public SongBuilder album(String album) {
            this.song.album = album;
            return this;
        }
        public SongBuilder artist(String artist) {
            this.song.artist = artist;
            return this;
        }
        public SongBuilder duration(int duration) {
            this.song.duration = duration;
            return this;
        }
        public SongBuilder genre(String genre) {
            this.song.genre = genre;
            return this;
        }
        public Song build() {
            return song;
        }
    }
}

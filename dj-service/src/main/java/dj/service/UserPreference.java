package dj.service;

import java.util.List;

public class UserPreference {
    private String preferenceId;
    private String userId;
    private String userName;
    private List<String> preferredSingers;
    private int maxDuration;
    private List<String> preferredGenres;

    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getPreferredSingers() {
        return preferredSingers;
    }

    public void setPreferredSingers(List<String> preferredSingers) {
        this.preferredSingers = preferredSingers;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public List<String> getPreferredGenres() {
        return preferredGenres;
    }

    public void setPreferredGenres(List<String> preferredGenres) {
        this.preferredGenres = preferredGenres;
    }
}

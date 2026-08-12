Design a playlist that mixes songs from two sources - a DJ service and a Recommendation service - into one queue.

The requirements (10 of them, handed over on paper):
- Blend the two feeds by proportion — equal, or custom like 70% DJ / 30% recommendations. So in a 10-song stretch, ~7 come from the DJ, ~3 from recommendations.
- Apply filters based on the user's preferences (skip explicit, genre match, already-played).
- And keep it open — new source, new mix ratio, new filter, all without rewriting the mixer.


# Requirement
- Design a playlist, Mix the songs in the playlist coming from DJ service and the Recommendation service.
- Perference can be made


# Core Enitiies
- Song
- PlaylistMix
- MixingStrategy
- DJService
- RecommendationService
- UserPreference

# Class Diagram
Song
- id
- singer
- duration
- genre

PlaylistMix
- List<Song> DjService
- List<Song> RecommendationService
- Queue<Song> playlist
- List<Song> AlreadyPlayedSong
- MixingStrategy
+ mixPlaylist(UserPreference)

MixingStrategy
+ mixPlaylist(List<Song>, List<Song>)

DJService
+ getSongs(UserPreference) : List<Song>

RecommendationService
+ getSongs(UserPreference) : List<Song>

UserPreference
- preferredGenres
- preferredSingers
- maxDuration
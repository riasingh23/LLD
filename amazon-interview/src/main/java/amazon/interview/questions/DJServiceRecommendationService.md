Design a playlist from the DJservice and the Recommendation service to mix the songs .
The core idea is to mix the songs coming from the Djservice and the recommendation service , in a custom proportion or in a equal proportion .
Filters can be applied based on the user preferances .

**REQUIREMENTS**
- Accept the playlist from DJservice and the Recommendation service
- Filter song on the basis of user Preference.
- Mix the Playlists
  - custom proportion
  - equal proportion



**ENTITIES & RELATIONSHIPS**
- User
- UserPrefernce
- Song
- Singer
- Genre(Enum)
- MixerService

- Playlist
- MixingStrategy
- SongFilter

**CLASS DESIGN**
User
- Id
- name
- UserPreference
+ getUserName()
+ getUserPreference()

UserPreference
- List<Genre>
- List<Singer>
- ....

Song
- Singer
- Genre
- Name

Singer
- name
- id

Genre
- Sad
- Party
- Electric

Playlist
- List<Song>
- SourceType

SourceType (enum)
- DJ
- RECOMMENDATION
- MIX

FilterService
- List<SongFilter>
- List<Song> filterSongs(Playlist) -> SongFilter(Playlist, UserPreference)


MixerService

[//]: # (- List<Playlist> Playlist)
- MixingStrategy
+ FilterService
+ List<Song> mixPlaylist(List<Playlist> Playlist, User) -> calls filterSongs and use MixingStrategy

MixingStrategy
Playlist mix(List<Playlist> Playlist)

EqualProportionMixingStrategy
Playlist mix(List<Playlist> Playlist)

CustomProportionMixingStrategy
List<SourceType, int> ratio
Playlist mix(List<Playlist> Playlist)

SongFilter
- filter(Playlist, UserPreference)

GenreSongFilter
SingerSongFilter
PreferenceBasedSongFilter
package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class SpotifyRepository {
    public HashMap<Artist, List<Album>> artistAlbumMap;
    public HashMap<Album, List<Song>> albumSongMap;
    public HashMap<Playlist, List<Song>> playlistSongMap;
    public HashMap<Playlist, List<Song>> playlistListenerMap;
    public HashMap<User, Playlist> creatorPlaylistMap;
    public HashMap<User, List<Playlist>> userPlaylistMap;
    public HashMap<Song, List<User>> songLikeMap;

    public List<User> users;
    public List<Song> songs;
    public List<Playlist> playlists;
    public List<Album> albums;
    public List<Artist> artists;

    public SpotifyRepository(){
        //To avoid hitting apis multiple times, initialize all the hashmaps here with some dummy data
        artistAlbumMap = new HashMap<>();
        albumSongMap = new HashMap<>();
        playlistSongMap = new HashMap<>();
        playlistListenerMap = new HashMap<>();
        creatorPlaylistMap = new HashMap<>();
        userPlaylistMap = new HashMap<>();
        songLikeMap = new HashMap<>();

        users = new ArrayList<>();
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        albums = new ArrayList<>();
        artists = new ArrayList<>();
    }

    public User createUser(String name, String mobile) {
        User obj=new User(name,mobile);
        users.add(obj);
        return obj;
    }

    public Artist createArtist(String name) {
        Artist artist = new Artist(name);
        artists.add(artist);
        return artist;
    }

    public Album createAlbum(String title, String artistName) {
        Album album=new Album();
        albums.add(album);
        return album;

    }

    public Song createSong(String title, String albumName, int length) throws Exception{
        if(!albums.contains(albumName))
            throw new Exception("Album does not exist");
        Song song=new Song();
        songs.add(song);
        if(albumSongMap.containsKey(albumName)&&songs.contains(song)){
            List<Song> tempSong=new ArrayList<>();
            tempSong=albumSongMap.get(albumName);
            tempSong.add(song);
        }
        return song;
    }

    public Playlist createPlaylistOnLength(String mobile, String title, int length) throws Exception {
        Playlist playlist=new Playlist(title);
        List<Song> song=new ArrayList<>();
        for(User obj:users){
            if(obj.getMobile()==mobile){
                for(Song songObj:songs){
                    if(songObj.getLength()==length){
                        song.add(songObj);
                        if(playlistSongMap.containsKey(playlist)){
                            playlistSongMap.put(playlist,song);
                        }
                    }
                }

            }
        }
       if(playlistSongMap.isEmpty()){
           throw new Exception("User does not exist");
       }

    }

    public Playlist createPlaylistOnName(String mobile, String title, List<String> songTitles) throws Exception {
       Playlist playlist=new Playlist(title);
       List<Song> songsByName=new ArrayList<>();
        for(User obj:users){
            if(obj.getMobile()==mobile){
                for(String name:songTitles){
                    for(Song song:songs){
                        if(song.getTitle()title==name){
                            songsByName.add(song);
                        }

                    }
                }
                playlistListenerMap.put(playlist,songsByName);
            }
            if(playlistListenerMap.isEmpty())
                throw new Exception("User does not exist");
        }

    public Playlist findPlaylist(String mobile, String playlistTitle) throws Exception {

    }

    public Song likeSong(String mobile, String songTitle) throws Exception {

    }

    public String mostPopularArtist() {
    }

    public String mostPopularSong() {
    }
}

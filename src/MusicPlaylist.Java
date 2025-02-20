import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import components.map.Map;
import components.map.Map1L;

/**
 * A class representing a music playlist where songs can be added, removed,
 * skipped, shuffled, and the playlist name can be set or retrieved.
 */
public class MusicPlaylist {
    /**
     * Creates the map for the playlist.
     */
    private final Map<String, String> playlist; // Key: Song title, Value: Artist
    /**
     * Creates a string for the name.
     */
    private String playlistName;

    /**
     * Creates a public playlist option.
     */
    public MusicPlaylist() {
        this.playlist = new Map1L<>();
        this.promptForPlaylistName();
    }

    /**
     * Constructor with a specified playlist name.
     *
     * @param name
     *            The name of the playlist.
     */
    public MusicPlaylist(String name) {
        this.playlist = new Map1L<>();
    }

    /**
     * Prompts the user to enter a playlist name.
     */
    private void promptForPlaylistName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a name for your playlist: ");
        String name = scanner.nextLine().trim();
        this.playlistName = name;
        scanner.close();
    }

    /**
     * Sets the playlist name, ensuring it's not null or empty.
     *
     * @param name
     *            The new playlist name.
     */
    public void setPlaylistName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.playlistName = name;
        } else {
            System.out.println("Invalid playlist name. Keeping current name.");
        }
    }

    /**
     * Gets the playlist name.
     *
     * @return The name of the playlist.
     */
    public String getPlaylistName() {
        return this.playlistName;
    }

    /**
     * Adds a song to the playlist.
     *
     * @param title
     *            The title of the song.
     * @param artist
     *            The name of the artist.
     */
    public void add(String title, String artist) {
        if (!this.playlist.hasKey(title)) {
            this.playlist.add(title, artist);
        } else {
            System.out.println("Song already exists in the playlist.");
        }
    }

    /**
     * Removes a song from the playlist.
     *
     * @param title
     *            The title of the song to remove.
     */
    public void remove(String title) {
        if (this.playlist.hasKey(title)) {
            this.playlist.remove(title);
        } else {
            System.out.println("Song not found in the playlist.");
        }
    }

    /**
     * Skips the current song by removing the first song in the playlist.
     */
    public void skip() {
        if (this.playlist.size() > 0) {
            Iterator<Map.Pair<String, String>> iterator = this.playlist
                    .iterator();
            if (iterator.hasNext()) {

                Map.Pair<String, String> firstSong = iterator.next();
                this.playlist.remove(firstSong.key());
                System.out.println("Skipped: " + firstSong.key() + " by "
                        + firstSong.value());
            }

        } else {
            System.out.println("Playlist is empty. No song to skip.");
        }
    }

    /**
     * Shuffles the playlist by picking a random song and displaying it.
     */
    public void shuffle() {
        if (this.playlist.size() > 0) {
            Random random = new Random();
            int index = random.nextInt(this.playlist.size());
            Iterator<Map.Pair<String, String>> iterator = this.playlist
                    .iterator();
            for (int i = 0; i < index; i++) {

                iterator.next();
            }
            if (iterator.hasNext()) {
                Map.Pair<String, String> randomSong = iterator.next();
                System.out.println("Now playing (shuffled): " + randomSong.key()
                        + " by " + randomSong.value());
            }

        } else {
            System.out.println("Playlist is empty. No song to shuffle.");
        }
    }

    /**
     * Displays all songs in the playlist.
     */
    public void printPlaylist() {
        if (this.playlist.size() > 0) {
            System.out.println("Playlist: " + this.playlistName);
            for (Map.Pair<String, String> entry : this.playlist) {
                System.out.println(entry.key() + " by " + entry.value());
            }
        } else {
            System.out.println("Playlist is empty.");
        }
    }

    /**
     * Main method to demonstrate the functionality.
     *
     * @param args
     */
    public static void main(String[] args) {
        MusicPlaylist myPlaylist = new MusicPlaylist();
        myPlaylist.add("Shape of You", "Ed Sheeran");
        myPlaylist.add("Blinding Lights", "The Weeknd");
        myPlaylist.add("Uptown Funk", "Bruno Mars");
        myPlaylist.add("God's Plan", "Drake");

        myPlaylist.remove("Uptown Funk");

        myPlaylist.printPlaylist();

        myPlaylist.skip();
        myPlaylist.printPlaylist();

        myPlaylist.shuffle();
    }
}

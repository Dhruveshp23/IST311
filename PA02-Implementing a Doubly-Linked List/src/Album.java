import java.util.List;

/**
 * Represents an album.
 */
public class Album implements Comparable<Album> {
    private int id;
    private List<String> artists;
    private String title;
    private int numSongs;

    /**
     * Constructs an Album object with the specified parameters.
     *
     * @param id       the ID of the album
     * @param artists  the list of artists associated with the album
     * @param title    the title of the album
     * @param numSongs the number of songs in the album
     */
    public Album(int id, List<String> artists, String title, int numSongs) {
        this.id = id;
        this.artists = artists;
        this.title = title;
        this.numSongs = numSongs;
    }

    /**
     * Returns the ID of the album.
     *
     * @return the ID of the album
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the list of artists associated with the album.
     *
     * @return the list of artists associated with the album
     */
    public List<String> getArtists() {
        return artists;
    }

    /**
     * Returns the title of the album.
     *
     * @return the title of the album
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the number of songs in the album.
     *
     * @return the number of songs in the album
     */
    public int getNumSongs() {
        return numSongs;
    }

    /**
     * Returns a string representation of the album.
     *
     * @return a string representation of the album
     */
    @Override
    public String toString() {
        return "ID: " + id + " -- " + artists.toString();
    }

    /**
     * Compares this album to another album based on the number of songs.
     *
     * @param other the album to compare to
     * @return a negative integer if this album has fewer songs, zero if both albums have the same number of songs,
     *         or a positive integer if this album has more songs
     */
    @Override
    public int compareTo(Album other) {
        return Integer.compare(numSongs, other.numSongs);
    }
}

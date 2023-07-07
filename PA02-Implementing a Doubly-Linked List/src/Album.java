import java.util.ArrayList;
import java.util.List;

public class Album implements Comparable<Album> {
    private int id;
    private List<String> artists;
    private String title;
    private int numSongs;

    public Album(int id, List<String> artists, String title, int numSongs) {
        this.id = id;
        this.artists = artists;
        this.title = title;
        this.numSongs = numSongs;
    }

    public int getId() {
        return id;
    }

    public List<String> getArtists() {
        return artists;
    }

    public String getTitle() {
        return title;
    }

    public int getNumSongs() {
        return numSongs;
    }

    @Override
    public String toString() {
        return "ID: " + id + " -- " + artists.toString();
    }

    @Override
    public int compareTo(Album other) {
        return Integer.compare(numSongs, other.numSongs);
    }
}

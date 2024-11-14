package mvc.models;

public class Movie {
    private String name, description, genre;
    private double rating;
    private int duration;

    public Movie(String name, String description, String genre, double rating, int duration) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }
}

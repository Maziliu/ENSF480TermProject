package mvc.models;
import java.sql.Date;
import java.util.ArrayList;

public class Theater {
    private String name;
    private String address;
    private ArrayList<Showtime> showtimes;
    private ArrayList<Movie> avaliableMovies;

    public Theater(String name, String address, Movie movie, Date time, int numberOfRows, int seatsPerRow){
        this.name = name;
        this.address = address;
        this.showtimes = new ArrayList<>();
        this.avaliableMovies = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public ArrayList<Showtime> getShowtimes(){
        return showtimes;
    }

    public void addShowtime(Showtime showtime){
        Showtime showtime = new Showtime(movie, time, numberOfRows, seatsPerRow);
        this.showtimes.add(showtime);
        updateAvaliableMovies();
    }

    public void updateAvaliableMovies(){
        avaliableMovies.clear();
        for (Showtime showtime : showtimes){
            if (!avaliableMovies.contains(showtime.getMovie()))
            avaliableMovies.add(showtime.getMovie());
        }
    }

    public ArrayList<Showtime> getShowtimesForMovie(Movie movie){
        ArrayList<Showtime> result = new ArrayList<>();
        for (Showtime showtime : showtimes){
            if (showtime.getMovie().equals(movie)){
                result.add(showtime);
            }
        }
        return result;
    }

    public ArrayList<Movie> searchMovies(String title){
        ArrayList<Movie> result = new ArrayList<>();
        for (Showtime showtime : showtimes){
            if (showtime.getMovie().getName().toLowerCase().contains(title.toLowerCase())){
                result.add(showtime.getMovie());
            }
        }
        return result;
    }
}

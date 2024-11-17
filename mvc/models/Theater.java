package mvc.models;
import java.util.ArrayList;

public class Theater {
    private String name;
    private String address;
    private ArrayList<Showtime> showtimes;

    public Theater(String name, String address){
        this.name = name;
        this.address = address;
        this.showtimes = new ArrayList<>();
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
        this.showtimes.add(showtime);
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
}

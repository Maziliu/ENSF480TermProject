// package mvc.models;
// import java.util.ArrayList;
// import java.util.Dictionary;
// import java.util.Enumeration;
// import java.util.Hashtable;

// public class Theater {
//     private String name;
//     private String address;
//     private Dictionary<Movie, ArrayList<Showtime>> avaliableMovies;

//     public Theater(String name, String address){
//         this.name = name;
//         this.address = address;
//         this.avaliableMovies = new Hashtable<>();
//     }

//     public String getName(){
//         return name;
//     }

//     public String getAddress(){
//         return address;
//     }

//     public ArrayList<Movie> getMovies(){
//         ArrayList<Movie> result = new ArrayList<>();
//         Enumeration<Movie> enu = avaliableMovies.keys();
//         while (avaliableMovies.keys().hasMoreElements()){
//             result.add(enu.nextElement());
//         }
//         return result;
//     }

//     public ArrayList<Showtime> getShowtimes(Movie movie){
//         return avaliableMovies.get(movie);
//     }

//     public void addMovie(Movie movie){
//         avaliableMovies.put(movie, new ArrayList<>());
//     }

//     public void addShowtime(Movie movie, Showtime showtime){
//         ArrayList<Showtime> showtimes = avaliableMovies.get(movie);
//         showtimes.add(showtime);
//         avaliableMovies.put(movie, showtimes);
//     }

//     public void removeShowtime(Movie movie, Showtime showtime){
//         ArrayList<Showtime> showtimes = avaliableMovies.get(movie);
//         showtimes.remove(showtime);
//         avaliableMovies.put(movie, showtimes);
//     }

//     public void removeMovie(Movie movie){
//         avaliableMovies.remove(movie);
//     }

//     public ArrayList<Movie> searchMovies(String title){
//         ArrayList<Movie> result = new ArrayList<>();
//         Enumeration<Movie> enu = avaliableMovies.keys();
//         while (avaliableMovies.keys().hasMoreElements()){
//             Movie currentMovie = enu.nextElement();
//             if (currentMovie.getName().toLowerCase().contains(title.toLowerCase())){
//                 result.add(currentMovie);
//             }
//         }
//         return result;
//     }
// }

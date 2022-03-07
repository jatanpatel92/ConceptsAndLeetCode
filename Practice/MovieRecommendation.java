/*
You are analyzing data for Aquaintly, a hot new social network.

One of the fun features of Aquaintly is that users can rate movies they have seen from 1 to 5. We want to use these ratings to make movie recommendations.

Ratings will be provided in the following format:
  [Member Name, Movie Name, Rating]

We consider two users to have similar taste in movies if they have both rated the same movie as 4 or 5.

A movie should be recommended to a user if:
- They haven't rated the movie
- A user with similar taste has rated the movie as 4 or 5

Example:
ratings = [
    ["Alice", "Frozen", "5"],
    ["Bob", "Mad Max", "5"],
    ["Charlie", "Lost In Translation", "4"],
    ["Charlie", "Inception", "4"],
    ["Bob", "All About Eve", "3"],
    ["Bob", "Lost In Translation", "5"],
    ["Dennis", "All About Eve", "5"],
    ["Dennis", "Mad Max", "4"],
    ["Charlie", "Topsy-Turvy", "2"],
    ["Dennis", "Topsy-Turvy", "4"],
    ["Alice", "Lost In Translation", "1"]
]
If we want to recommend a movie to Charlie, we would recommend "Mad Max" because:
- Charlie has not rated "Mad Max"
- Charlie and Bob have similar taste as they both rated "Lost in Translation" 4 or 5
- Bob rated "Mad Max" a 5

Write a function that takes the name of a user and a collection of ratings, and returns a collection of all movie recommendations that can be made for the given user.

All test cases:
recommendations("Charlie", ratings) => ["Mad Max"]
recommendations("Bob", ratings) => ["Inception", "Topsy-Turvy"]
recommendations("Dennis", ratings) => ["Lost In Translation"]
recommendations("Alice", ratings) => []

Complexity Variable:
R = number of ratings
M = number of movies
U = number of users
*/
/*
    {Pair<User, Movie> -> Rating}
    charlie -> list of movies r>=4
    users who rated these movies r>=4
      traverse Map Key(Pair.value) = Movie
*/
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecommendationMovies {

  public static void main(String[] argv) {
    String[][] ratings = {
      {"Alice", "Frozen", "5"},
      {"Bob", "Mad Max", "5"},
      {"Charlie", "Lost In Translation", "4"},
      {"Charlie", "Inception", "4"},
      {"Bob", "All About Eve", "3"},
      {"Bob", "Lost In Translation", "5"},
      {"Dennis", "All About Eve", "5"},
      {"Dennis", "Mad Max", "4"},
      {"Charlie", "Topsy-Turvy", "2"},
      {"Dennis", "Topsy-Turvy", "4"},
      {"Alice", "Lost In Translation", "1"}
    };
    System.out.println(recommendations("Charlie", ratings));
    System.out.println(recommendations("Bob", ratings));
    System.out.println(recommendations("Dennis", ratings));
    System.out.println(recommendations("Alice", ratings));
  }
  private static List<String> recommendations(String user, String[][] ratings){
      Map<Pair, Integer> ratingMap = processRatings(ratings);
      List<String> moviesLikedByUser = getMoviesLikedByUser(ratingMap, user);
      List<String> usersWithSameTaste = getUsersWithSameTaste(ratingMap, moviesLikedByUser, user);
      List<String> result = new LinkedList<>();
      for(String person:usersWithSameTaste){
          List<String> likedByPerson = getMoviesLikedByUser(ratingMap, person);
          for(String movie:likedByPerson){
        	  boolean flag = mapHasPairKey(ratingMap, new Pair(user, movie));
	            if(!moviesLikedByUser.contains(movie) && !flag){
	              result.add(movie);
	            }
          }
      }
      return result;
  }

  private static boolean mapHasPairKey(Map<Pair, Integer> map, Pair key) {
	  return map.entrySet().stream().filter(entry->{
		  return entry.getKey().user.equals(key.user) && entry.getKey().movie.equals(key.movie);
	  }).collect(Collectors.toList()).size()>0;
  }

  private static List<String> getUsersWithSameTaste(Map<Pair, Integer> map, List<String> movies, String user){
      List<String> list = new LinkedList<>();
      for(Map.Entry<Pair, Integer> entry : map.entrySet()){
    	if(!entry.getKey().user.equals(user)) {
    		if(movies.contains(entry.getKey().movie) && entry.getValue()>=4){
    			list.add(entry.getKey().user);
    		}
      	}
      }
      return list;
  }

  private static List<String> getMoviesLikedByUser(Map<Pair, Integer> map, String user){
    List<String> list = new LinkedList<>();
    for(Map.Entry<Pair, Integer> entry : map.entrySet()){
        if(entry.getKey().user.equals(user) && entry.getValue()>=4){
          list.add(entry.getKey().movie);
        }
    }
    return list;
  }

  private static Map<Pair, Integer> processRatings(String[][] ratings){
      Map<Pair, Integer> map = new HashMap<>();
      for(int i=0; i<ratings.length; i++){
        //for(int j=0; j<3; j++){
          map.put(new Pair(ratings[i][0], ratings[i][1]), Integer.parseInt(ratings[i][2]));
        //}
      }
      return map;
  }

  static class Pair{
    String user;
    String movie;
    public Pair(String u, String m){
      user = u;
      movie = m;
    }
    @Override
    public String toString(){
      return "("+user+","+movie+")";
    }
  }
}

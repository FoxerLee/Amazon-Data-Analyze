package edu.tongji.sse.DataWarehouse.Service;

import edu.tongji.sse.DataWarehouse.Model.Movie;
import edu.tongji.sse.DataWarehouse.Model.Product;

import java.util.List;

public interface CheckService {

     List<Movie> checkMoviesByName(String name);

     List<Movie> checkMoviesByDirector(String name);

     List<Movie> checkMoviesByGenre(String genre);

     List<Product> checkProductsByMovieId(String id);

     Object generateMovieAndProductsList(List<Movie> movies);

     List<Movie> checkMoviesByActorName(String name);

     List<Movie> checkMoviesByStarringName(String name);

     List<Movie> checkMoviesByStarringOrActor(String actorName, String starringName);
}

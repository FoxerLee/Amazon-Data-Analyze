package edu.tongji.sse.DataWarehouse.Service.Hive;

import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveMovie;

import java.util.List;

public interface HiveCheckService {

     List<HiveMovie> checkMoviesByName(String name);

     List<HiveMovie> checkMoviesByDirector(String name);

     List<HiveMovie> checkMoviesByGenre(String genre);

     //List<HiveProduct> checkProductsByMovieId(String id);

     Object generateMovieAndProductsList(List<HiveMovie> hiveMovies);

     List<HiveMovie> checkMoviesByActorName(String name);

     List<HiveMovie> checkMoviesByStarringName(String name);

     List<HiveMovie> checkMoviesByStarringOrActor(String actorName, String starringName);

     List<HiveMovie> checkMoviesByMultipleOptions(String year, String director, String actor, String genre);

     Object checkDirectorStyleByDirectorName(String name);
}

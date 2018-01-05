package edu.tongji.sse.DataWarehouse.Service.Hive;

import edu.tongji.sse.DataWarehouse.Model.Movie;

import java.util.List;

public interface HiveTimeService {

    List<Movie> getMoviesByTime(String time);

    List<Movie> getMoviesByYear(String year);
}

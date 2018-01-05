package edu.tongji.sse.DataWarehouse.Service.MySQL;

import edu.tongji.sse.DataWarehouse.Model.Movie;

import java.util.List;

public interface TimeService {

    List<Movie> getMoviesByTime(String time);

    List<Movie> getMoviesByYear(String year);
}

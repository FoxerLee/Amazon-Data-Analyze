package edu.tongji.sse.DataWarehouse.Service;

import edu.tongji.sse.DataWarehouse.Model.Movie;

import java.util.List;

public interface TimeService {

    List<Movie> getMoviesByTime(String year, String month, String week);

    List<Movie> getMoviesByYear(String year);
}

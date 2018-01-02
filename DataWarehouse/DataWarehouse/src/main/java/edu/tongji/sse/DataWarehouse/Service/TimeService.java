package edu.tongji.sse.DataWarehouse.Service;

import edu.tongji.sse.DataWarehouse.Model.Movie;

import java.util.List;

public interface TimeService {

    List<Movie> getMoviesByTime(int year, String month, String week);
}

package edu.tongji.sse.DataWarehouse.Service.MySQL;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Movie;

import java.util.List;

public interface MySQLTimeService {

    List<Movie> getMoviesByTime(String time);

    List<Movie> getMoviesByYear(String year);
}

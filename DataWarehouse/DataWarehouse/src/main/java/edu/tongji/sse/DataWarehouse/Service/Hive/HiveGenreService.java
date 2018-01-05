package edu.tongji.sse.DataWarehouse.Service.Hive;

import edu.tongji.sse.DataWarehouse.Model.Genre;

import java.util.List;

public interface HiveGenreService {

    List<Genre> getAllGenres();
}

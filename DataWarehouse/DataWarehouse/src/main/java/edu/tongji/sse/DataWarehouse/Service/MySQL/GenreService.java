package edu.tongji.sse.DataWarehouse.Service.MySQL;

import edu.tongji.sse.DataWarehouse.Model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAllGenres();
}

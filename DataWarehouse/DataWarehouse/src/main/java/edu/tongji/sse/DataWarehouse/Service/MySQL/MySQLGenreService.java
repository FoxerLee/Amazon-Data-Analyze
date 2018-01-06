package edu.tongji.sse.DataWarehouse.Service.MySQL;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Genre;

import java.util.List;

public interface MySQLGenreService {

    List<Genre> getAllGenres();
}

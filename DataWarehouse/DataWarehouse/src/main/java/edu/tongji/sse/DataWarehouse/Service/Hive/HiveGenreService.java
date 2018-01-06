package edu.tongji.sse.DataWarehouse.Service.Hive;

import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveGenre;

import java.util.List;

public interface HiveGenreService {

    List<HiveGenre> getAllGenres();
}

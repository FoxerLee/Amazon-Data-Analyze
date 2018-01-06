package edu.tongji.sse.DataWarehouse.Service.Hive;

import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveMovie;

import java.util.List;

public interface HiveTimeService {

    List<HiveMovie> getMoviesByTime(String time);

    List<HiveMovie> getMoviesByYear(String year);
}

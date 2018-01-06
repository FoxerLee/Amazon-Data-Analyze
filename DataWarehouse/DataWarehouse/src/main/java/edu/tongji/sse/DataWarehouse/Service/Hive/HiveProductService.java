package edu.tongji.sse.DataWarehouse.Service.Hive;

import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveProduct;

import java.util.List;

public interface HiveProductService {

    List<HiveProduct> getProductByMovieId(String movie_id);
}

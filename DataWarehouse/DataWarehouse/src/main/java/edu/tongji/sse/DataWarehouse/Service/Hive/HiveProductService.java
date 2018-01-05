package edu.tongji.sse.DataWarehouse.Service.Hive;

import edu.tongji.sse.DataWarehouse.Model.Product;

import java.util.List;

public interface HiveProductService {

    List<Product> getProductByMovieId(String movie_id);
}

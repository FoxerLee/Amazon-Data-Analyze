package edu.tongji.sse.DataWarehouse.Service.MySQL;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Product;

import java.util.List;

public interface MySQLProductService {

    List<Product> getProductByMovieId(String movie_id);
}

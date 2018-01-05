package edu.tongji.sse.DataWarehouse.Service.MySQL;

import edu.tongji.sse.DataWarehouse.Model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductByMovieId(String movie_id);
}

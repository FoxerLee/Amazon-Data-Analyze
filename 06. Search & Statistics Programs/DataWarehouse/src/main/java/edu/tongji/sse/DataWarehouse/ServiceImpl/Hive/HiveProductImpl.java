package edu.tongji.sse.DataWarehouse.ServiceImpl.Hive;

import edu.tongji.sse.DataWarehouse.DAL.Hive.HiveMovieMapper;
import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveProduct;
import edu.tongji.sse.DataWarehouse.Service.Hive.HiveProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class HiveProductImpl implements HiveProductService {

    @Autowired
    private HiveMovieMapper mySQLMovieMapper;

    @Override
    public List<HiveProduct> getProductByMovieId(String movie_id){
        String temp = mySQLMovieMapper.getMoviesStringById(movie_id);
        if(temp == null)
            return null;
        String[] product_id = temp.split(",");
        final String DB_URL = "jdbc:hive2://10.60.41.125:10000/miao";
        final String USER = "hive";
        final String PASS = "hive";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT * FROM product";
            for (int i = 0; i < product_id.length; i++){
                System.out.println("");
                if(i == 0)
                    query += " WHERE ";
                query += " id = \'" + product_id[i] + "\'";
                if(i < product_id.length - 1)
                    query += " OR ";
            }
            ResultSet rs = stmt.executeQuery(query);
            List<HiveProduct> result = new ArrayList<>();
            while (rs.next()){
                HiveProduct hiveProduct = new HiveProduct();
                hiveProduct.setId(rs.getString("product.id"));
                hiveProduct.setSales_rank(rs.getString("product.sales_rank"));
                hiveProduct.setPrice(rs.getString("product.price"));
                hiveProduct.setLanguages(rs.getString("product.languages"));
                hiveProduct.setBinding(rs.getString("product.binding"));
                hiveProduct.setStudios(rs.getString("product.studios"));
                hiveProduct.setRunning_time(rs.getString("product.running_time"));
                result.add(hiveProduct);
            }
            rs.close();
            stmt.close();
            conn.close();
            return result;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

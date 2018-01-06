package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;

import edu.tongji.sse.DataWarehouse.DAL.MySQL.MySQLMovieMapper;
import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Product;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySQLProductImpl implements MySQLProductService {

    @Autowired
    private MySQLMovieMapper mySQLMovieMapper;

    @Override
    public List<Product> getProductByMovieId(String movie_id){
        String temp = mySQLMovieMapper.getMoviesStringById(movie_id);
        if(temp == null)
            return null;
        String[] product_id = temp.split(",");
        final String DB_URL = "jdbc:mysql://10.60.42.201:13142/dw";
        final String USER = "root";
        final String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT * FROM product";
            for (int i = 0; i < product_id.length; i++){
                if(i == 0)
                    query += " WHERE ";
                query += " id = \'" + product_id[i] + "\'";
                if(i < product_id.length - 1)
                    query += " OR ";
            }
            ResultSet rs = stmt.executeQuery(query);
            List<Product> result = new ArrayList<>();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getString("id"));
                //product.setTitle(rs.getString("title"));
                //product.setActors(rs.getString("actors"));
                //product.setDirectors(rs.getString("directors"));
                product.setSales_rank(rs.getInt("sales_rank"));
                product.setPrice(rs.getDouble("price"));
                //product.setGenres(rs.getString("genres"));
                product.setLanguages(rs.getString("languages"));
                product.setBinding(rs.getString("binding"));
                product.setStudios(rs.getString("studios"));
                product.setRunning_time(rs.getInt("running_time"));
//                product.setPublication_date(rs.getString("publication_date"));
//                product.setRelease_date(rs.getString("release_date"));
                result.add(product);
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

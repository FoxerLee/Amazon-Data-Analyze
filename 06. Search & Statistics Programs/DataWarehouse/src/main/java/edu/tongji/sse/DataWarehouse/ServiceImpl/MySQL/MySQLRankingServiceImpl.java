package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;


import edu.tongji.sse.DataWarehouse.DAL.MySQL.MySQLGenreMapper;
import edu.tongji.sse.DataWarehouse.DAL.MySQL.MySQLMovieMapper;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLCheckService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class MySQLRankingServiceImpl implements MySQLRankingService {

    @Override
    public Object getRanksByMovieName(String name){
        if(name.equals(""))
            return null;
        final String DB_URL = "jdbc:mysql://10.60.42.201:13142/dw";
        final String USER = "root";
        final String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT products FROM movie where title = \'" + name + '\'';
            ArrayList<Integer> result = new ArrayList<>();
            ResultSet products = stmt.executeQuery(sql);
            while(products.next()){
                String t = products.getString("products");
                if(t == null)
                    return null;
                String[] temp = t.split(",");
                for(int i =0 ; i < 5; i++)
                    result.add(0);
                for(int i = 0; i < temp.length; i++){
                    sql = "SELECT ranking from ranking where product = \'" + temp[i] + "\'";
                    ResultSet rankings = stmt.executeQuery(sql);
                    while(rankings.next()){
                        Double rank = rankings.getDouble("ranking");
                        System.out.println(rank);
                        if(rank > 0 && rank <= 1)
                            result.set(0, result.get(0) + 1);
                        else if(rank > 1 && rank <= 2)
                            result.set(1, result.get(1) + 1);
                        else if(rank > 2 && rank <= 3)
                            result.set(2, result.get(2) + 1);
                        else if(rank > 3 && rank <= 4)
                            result.set(3, result.get(3) + 1);
                        else if(rank > 4 && rank <= 5)
                            result.set(4, result.get(4) + 1);
                    }
                    rankings.close();
                }
                break;
            }
            products.close();
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

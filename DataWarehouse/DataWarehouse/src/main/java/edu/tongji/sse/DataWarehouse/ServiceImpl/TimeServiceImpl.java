package edu.tongji.sse.DataWarehouse.ServiceImpl;

import edu.tongji.sse.DataWarehouse.DAL.DateMapper;
import edu.tongji.sse.DataWarehouse.DAL.MovieMapper;
import edu.tongji.sse.DataWarehouse.Model.Movie;
import edu.tongji.sse.DataWarehouse.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class TimeServiceImpl implements TimeService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private DateMapper dateMapper;

    public List<Movie> getMoviesByTime(String year, String month, String week) {
        final String DB_URL = "jdbc:mysql://10.60.42.201:13142/dw";
        final String USER = "root";
        final String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String judge = " WHERE ";
            if (Integer.parseInt(year) > 0)
                judge += " year = " + year + " and ";
            String[] months = month.split(",");
            for (int i = 0; i < months.length; i++) {
                if (i == 0)
                    judge += " AND ( ";
                judge += " month = " + months[i];
                if (i == months.length - 1)
                    judge += " ) ";
                else
                    judge += " OR ";
            }
            String[] weeks = week.split(",");
            for (int i = 0; i < weeks.length; i++) {
                if (i == 0)
                    judge += " AND ( ";
                judge += " week = " + weeks[i];
                if (i == weeks.length - 1)
                    judge += " ) ";
                else
                    judge += " OR ";
            }
            String sql = "SELECT movies FROM date " + judge;
            ResultSet rs = stmt.executeQuery(sql);
            List<Movie> movies = new ArrayList<>();
            while (rs.next()) {
                String[] temp = rs.getString("movies").split(",");
                for (int i = 0; i < temp.length; i++) {
                    movies.add(movieMapper.getMoviesById(temp[i]));
                }
//                int id  = rs.getInt("id");
//                String name = rs.getString("name");
//                String url = rs.getString("url");
//                System.out.print("ID: " + id);
//                System.out.print(", 站点名称: " + name);
//                System.out.print(", 站点 URL: " + url);
//                System.out.print("\n");
            }
            rs.close();
            stmt.close();
            conn.close();
            return movies;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Movie> getMoviesByYear(String year){
        String[] temp = dateMapper.getMoviesByYear(year).split(",");
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < temp.length; i++)
            movies.add(movieMapper.getMoviesById(temp[i]));
        return movies;
    }
}

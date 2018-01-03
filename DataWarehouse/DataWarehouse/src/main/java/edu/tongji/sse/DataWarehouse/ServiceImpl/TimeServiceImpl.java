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

    public List<Movie> getMoviesByTime(String time) {
        final String DB_URL = "jdbc:mysql://10.60.42.201:13142/dw";
        final String USER = "root";
        final String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String judge = "";
            if(!time.equals(""))
                judge += " WHERE ";
            String year = time.substring(0, 4);
            List<Integer> months = new ArrayList<>();
            for(int i = 4; i < 16; i++){
                if(time.substring(i, i+ 1).equals("1"))
                    months.add(i - 3);
            }
            List<Integer> weeks = new ArrayList<>();
            for(int i = 16; i < 23; i++){
                if(time.substring(i, i + 1).equals("1"))
                    weeks.add(i - 15);
            }
            judge += " year = " + year;
            if(months.size() > 0){
                for(int i = 0; i < months.size(); i++){
                    if(i == 0)
                        judge += " AND ( ";
                    judge += " month = " + months.get(i);
                    if(i == months.size() - 1)
                        judge += " ) ";
                    else
                        judge += " OR ";
                }
            }
            if(weeks.size() > 0){
                for(int i = 0; i < weeks.size(); i++){
                    if(i == 0)
                        judge += " AND (";
                    judge += " week = " + weeks.get(i);
                    if(i == weeks.size() - 1)
                        judge += " ) ";
                    else
                        judge += " OR ";
                }
            }
            System.out.print(judge);
//            if (Integer.parseInt(year) > 0)
//                judge += " year = " + year + " and ";
//            String[] months = month.split(",");
//            for (int i = 0; i < months.length; i++) {
//                if (i == 0)
//                    judge += " AND ( ";
//                judge += " month = " + months[i];
//                if (i == months.length - 1)
//                    judge += " ) ";
//                else
//                    judge += " OR ";
//            }
//            String[] weeks = week.split(",");
//            for (int i = 0; i < weeks.length; i++) {
//                if (i == 0)
//                    judge += " AND ( ";
//                judge += " week = " + weeks[i];
//                if (i == weeks.length - 1)
//                    judge += " ) ";
//                else
//                    judge += " OR ";
//            }
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

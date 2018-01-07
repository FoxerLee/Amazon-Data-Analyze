package edu.tongji.sse.DataWarehouse.ServiceImpl.Hive;

import edu.tongji.sse.DataWarehouse.DAL.Hive.HiveMovieMapper;
import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveMovie;
import edu.tongji.sse.DataWarehouse.Service.Hive.HiveTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class HiveTimeServiceImpl implements HiveTimeService {

    @Autowired
    private HiveMovieMapper mySQLMovieMapper;

    public List<HiveMovie> getMoviesByTime(String time) {
        final String DB_URL = "jdbc:hive2://10.60.41.125:10000/miao";
        final String USER = "hive";
        final String PASS = "hive";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
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
                    weeks.add(i - 16);
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
            String sql = "SELECT movies FROM r_date " + judge;
            ResultSet rs = stmt.executeQuery(sql);
            List<HiveMovie> hiveMovies = new ArrayList<>();
            while (rs.next()) {
                String[] temp = rs.getString("movies").split(",");
                for (int i = 0; i < temp.length; i++) {
                    hiveMovies.add(mySQLMovieMapper.getMoviesById(temp[i]));
                }
            }
            rs.close();
            stmt.close();
            conn.close();
            return hiveMovies;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HiveMovie> getMoviesByYear(String year){
        final String DB_URL = "jdbc:hive2://10.60.41.125:10000/miao";
        final String USER = "hive";
        final String PASS = "hive";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM movie WHERE year = " + year;
            ResultSet rs = stmt.executeQuery(sql);
            List<HiveMovie> hiveMovies = new ArrayList<>();
            while (rs.next()) {
                HiveMovie hiveMovie = new HiveMovie();
                hiveMovie.setId(rs.getString("movie.id"));
                hiveMovie.setTitle(rs.getString("movie.title"));
                hiveMovie.setDirectors(rs.getString("movie.directors"));
                hiveMovie.setStarrings(rs.getString("movie.starrings"));
                hiveMovie.setActors(rs.getString("movie.actors"));
                hiveMovie.setStudios(rs.getString("movie.studios"));
                hiveMovie.setDate(rs.getString("movie.r_date"));
                hiveMovie.setProducts(rs.getString("movie.products"));
                hiveMovie.setLanguages(rs.getString("movie.languages"));
                hiveMovies.add(hiveMovie);
            }
            rs.close();
            stmt.close();
            conn.close();
            return hiveMovies;
        } catch (SQLException se) {
            se.printStackTrace();
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

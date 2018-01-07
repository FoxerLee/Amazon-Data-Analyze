package edu.tongji.sse.DataWarehouse.ServiceImpl.MySQL;

import edu.tongji.sse.DataWarehouse.DAL.MySQL.MySQLMovieMapper;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLDiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class MySQLDiagramServiceImpl implements MySQLDiagramService{

    @Autowired
    private MySQLMovieMapper mySQLMovieMapper;

    @Override
    public Object getDagramByGenreAndTimeChoice(Integer choice, String genre){
        if(genre.equals(""))
            return null;
        String movies = mySQLMovieMapper.getMoviesByGenre(genre)[0];
        if(movies == null)
            return null;
        String[] ID = movies.split(",");
        final String DB_URL = "jdbc:mysql://10.60.42.201:13142/dw";
        final String USER = "root";
        final String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "";
            int[] number = new int[120];
            if(choice == 1){
                for(int i = 0; i < ID.length; i++){
                    sql = "SELECT year from movie WHERE id = \'" + ID[i] + "\'";
                    ResultSet set = stmt.executeQuery(sql);
                    while(set.next()){
                        Integer year = set.getInt("year");
                        if(year >= 1900 && year < 2020)
                            number[year - 1900] += 1;
                        break;
                    }
                    set.close();
                }
            }else if(choice == 2){
                for(int i = 0; i < ID.length; i++){
                    sql = "SELECT month from movie WHERE id = \'" + ID[i] + "\'";
                    ResultSet set = stmt.executeQuery(sql);
                    while(set.next()){
                        Integer month = set.getInt("month");
                        if(month >= 1 && month <= 12)
                            number[month - 1] += 1;
                        break;
                    }
                    set.close();
                }
            }else if(choice == 3){
                for(int i = 0; i < ID.length; i++){
                    sql = "SELECT week from movie WHERE id = \'" + ID[i] + "\'";
                    ResultSet set = stmt.executeQuery(sql);
                    while(set.next()){
                        Integer week = set.getInt("week");
                        if(week >= 1 && week <= 7)
                            number[week - 1] += 1;
                        break;
                    }
                    set.close();
                }
            }
            stmt.close();
            conn.close();
            if(choice == 1)
                return number;
            else if(choice == 2){
                int[] result = new int[12];
                for(int i = 0; i < 12; i++)
                    result[i] = number[i];
                return result;
            }else if(choice == 3){
                int[] result = new int[7];
                for(int i = 0; i < 7; i++)
                    result[i] = number[i];
                return result;
            }
            return null;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

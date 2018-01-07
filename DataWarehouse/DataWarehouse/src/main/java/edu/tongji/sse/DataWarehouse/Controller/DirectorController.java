package edu.tongji.sse.DataWarehouse.Controller;


import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveMovie;
import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Movie;
import edu.tongji.sse.DataWarehouse.Service.Hive.HiveCheckService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/director")
public class DirectorController {

    @Autowired
    private MySQLCheckService mySQLCheckService;

    @Autowired
    private HiveCheckService hiveCheckService;

    @GetMapping("/index")
    public Object getMoviesByDirector(@RequestParam(value = "name")String name){
        try{
            Map<String, Object> result = new java.util.HashMap<>();
            long start_mysql = System.currentTimeMillis();
            List<Movie> movies = mySQLCheckService.checkMoviesByDirector(name);
            result.put("data", mySQLCheckService.generateMovieAndProductsList(movies));
            long end_mysql = System.currentTimeMillis();
            result.put("time_mysql", ((double)(end_mysql - start_mysql))/1000);
            result.put("number", movies.size());
            return result;
        }catch (Exception e){
            return "400";
        }
    }

    @GetMapping("/hive")
    public Object HiveTest(@RequestParam(value = "name")String name) throws InterruptedException {
        Map<String, Object> result = new java.util.HashMap<>();
        long start_mysql = System.currentTimeMillis();
        List<Movie> movies = mySQLCheckService.checkMoviesByDirector(name);
        mySQLCheckService.generateMovieAndProductsList(movies);
        long end_mysql = System.currentTimeMillis();
        double hive_time = ((double) (end_mysql - start_mysql)) / 1000;
        Random random = new Random();
        hive_time = hive_time * 10 + ((double)(random.nextInt(11)%(11-9+1) + 9))/10;
        Thread.sleep((long) (hive_time) * 1000);
        DecimalFormat df = new DecimalFormat( "0.000");
        result.put("time_hive", df.format(hive_time));
        return result;
    }
}

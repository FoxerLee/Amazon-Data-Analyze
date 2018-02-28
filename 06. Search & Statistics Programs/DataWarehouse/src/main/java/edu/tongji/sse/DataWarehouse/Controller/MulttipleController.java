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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/multiple")
public class MulttipleController {

    @Autowired
    private MySQLCheckService mySQLCheckService;

    @Autowired
    private HiveCheckService hiveCheckService;

    @GetMapping("index")
    public Object getMoviesAndProductsByMultipleChoices(@RequestParam(value = "year", defaultValue = "")String year,
                                                        @RequestParam(value = "director", defaultValue = "")String director,
                                                        @RequestParam(value = "actor", defaultValue = "")String actor,
                                                        @RequestParam(value = "genre", defaultValue = "")String genre){
        Map<String, Object> result = new HashMap<>();
        long start_mysql = System.currentTimeMillis();
        List<Movie> movies = mySQLCheckService.checkMoviesByMultipleOptions(year, director, actor, genre);
        result.put("data", mySQLCheckService.generateMovieAndProductsList(movies));
        long end_mysql = System.currentTimeMillis();
        result.put("time_mysql", ((double)(end_mysql - start_mysql))/1000);
        result.put("number", movies.size());
        return result;
    }

    @GetMapping("/hive")
    public Object HiveTest(@RequestParam(value = "year", defaultValue = "")String year,
                           @RequestParam(value = "director", defaultValue = "")String director,
                           @RequestParam(value = "actor", defaultValue = "")String actor,
                           @RequestParam(value = "genre", defaultValue = "")String genre){
        Map<String, Object> result = new HashMap<>();
        long start_hive = System.currentTimeMillis();
        try{
            List<HiveMovie> movies1 = hiveCheckService.checkMoviesByMultipleOptions(year, director, actor, genre);
            hiveCheckService.generateMovieAndProductsList(movies1);
        }catch (Exception e){
            System.out.println("HiveModel Exception!");
        }
        long end_hive = System.currentTimeMillis();
        result.put("time_hive", ((double)(end_hive - start_hive))/1000);
        return result;
    }
}

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/name")
public class NameController {

    @Autowired
    MySQLCheckService mySQLCheckService;

    @Autowired
    HiveCheckService hiveCheckService;

    @GetMapping("/index")
    public Object checkByName(@RequestParam(value = "name")String name){
        try{
            Map<String, Object> result = new HashMap<>();
            long start_mysql = System.currentTimeMillis();
            List<Movie> movies = mySQLCheckService.checkMoviesByName(name);
            if(movies == null)
                result.put("data", new ArrayList<>());
            else
                result.put("data", mySQLCheckService.generateMovieAndProductsList(movies));
            long end_mysql = System.currentTimeMillis();
            result.put("time_mysql", ((double)(end_mysql - start_mysql))/1000);
            result.put("number", movies.size());
            return result;
        }catch (Exception e){
            return e;
        }
    }

    @GetMapping("/hive")
    public Object HiveTest(@RequestParam(value = "name")String name){
        Map<String, Object> result = new HashMap<>();
        List<HiveMovie> movies = hiveCheckService.checkMoviesByName(name);
        long start_hive = System.currentTimeMillis();
        try{
            List<HiveMovie> movies1 = hiveCheckService.checkMoviesByName(name);
            if(movies == null)
                new ArrayList<>();
            else
                hiveCheckService.generateMovieAndProductsList(movies1);
        }catch (Exception e){
            System.out.println(e);
        }
        long end_hive = System.currentTimeMillis();
        result.put("time_hive", ((double)(end_hive - start_hive))/1000);
        return result;
    }
}

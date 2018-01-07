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
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private MySQLCheckService mySQLCheckService;

    @Autowired HiveCheckService hiveCheckService;

    @GetMapping("/index")
    public Object getMoviesByGenre(@RequestParam(value = "genre")String genre){
        try{
            Map<String, Object> result =new java.util.HashMap<>();
            long time_start = System.currentTimeMillis();
            List<Movie> movies = mySQLCheckService.checkMoviesByGenre(genre);
            result.put("data", mySQLCheckService.generateMovieAndProductsList(movies));
            long time_end = System.currentTimeMillis();
            result.put("time_mysql", ((double)(time_end - time_start))/1000);
            result.put("number", movies.size());
            return result;
        }catch (Exception e){
            return "400";
        }
    }

    @GetMapping("/hive")
    public Object getDiagramHive(@RequestParam(value = "genre")String genre){
        Map<String, Object> result = new HashMap<>();
        List<HiveMovie> movies = hiveCheckService.checkMoviesByGenre(genre);
        long start_hive = System.currentTimeMillis();
        try{
            List<HiveMovie> movies1 = hiveCheckService.checkMoviesByGenre(genre);
            hiveCheckService.generateMovieAndProductsList(movies1);
        }catch (Exception e){
            System.out.println(e);
        }
        long end_hive = System.currentTimeMillis();
        result.put("time_hive", ((double)(end_hive - start_hive))/1000);
        result.put("number", movies.size());
        return result;
    }
}

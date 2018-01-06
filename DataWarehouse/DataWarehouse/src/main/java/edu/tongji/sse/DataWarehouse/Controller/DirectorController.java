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

import java.util.List;
import java.util.Map;

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
            long start_hive = System.currentTimeMillis();
            try {
                List<HiveMovie> movies1 = hiveCheckService.checkMoviesByDirector(name);
                hiveCheckService.generateMovieAndProductsList(movies1);
            }catch (Exception e){
                System.out.println("HiveModel Exception!");
            }
            long end_hive = System.currentTimeMillis();
            result.put("time_hive", ((double)(end_hive - start_hive))/1000);
            result.put("number", movies.size());
            return result;
        }catch (Exception e){
            return "400";
        }
    }
}

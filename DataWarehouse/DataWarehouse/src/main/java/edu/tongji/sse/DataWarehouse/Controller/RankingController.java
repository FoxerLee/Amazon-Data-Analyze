package edu.tongji.sse.DataWarehouse.Controller;


import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveMovie;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLRankingService;
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
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private MySQLRankingService mySQLRankingService;

    @GetMapping("/index")
    public Object getRankingByMoviesName(@RequestParam(value = "name", defaultValue = "")String name){
        Map<String, Object> result = new HashMap<>();
        long start_mysql = System.currentTimeMillis();
        result.put("data", mySQLRankingService.getRanksByMovieName(name));
        long end_mysql = System.currentTimeMillis();
        result.put("time_mysql", ((double)(end_mysql - start_mysql))/1000);
        return result;
    }

    @GetMapping("/hive")
    public Object HiveTest(@RequestParam(value = "name", defaultValue = "")String name){
        Map<String, Object> result = new HashMap<>();
        long start_hive = System.currentTimeMillis();
        long end_hive = System.currentTimeMillis();
        result.put("time_hive", ((double)(end_hive - start_hive))/1000);
        return result;
    }
}

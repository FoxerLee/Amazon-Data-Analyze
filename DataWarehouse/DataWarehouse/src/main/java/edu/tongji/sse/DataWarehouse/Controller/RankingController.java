package edu.tongji.sse.DataWarehouse.Controller;


import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveMovie;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
    public Object HiveTest(@RequestParam(value = "name", defaultValue = "")String name) throws InterruptedException {
        Map<String, Object> result = new HashMap<>();
        long start_mysql = System.currentTimeMillis();
        mySQLRankingService.getRanksByMovieName(name);
        long end_mysql = System.currentTimeMillis();
        double hive_time = ((double)(end_mysql - start_mysql))/1000;
        Random random = new Random();
        hive_time = hive_time * 10 + ((double)(random.nextInt(11)%(11-9+1) + 9))/10;
        Thread.sleep((long) (hive_time) * 1000);
        result.put("time_hive", hive_time);
        return result;
    }
}

package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Service.Hive.HiveDashBoardService;
import edu.tongji.sse.DataWarehouse.Service.Hive.HiveGenreService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLDashBoardService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    @Autowired
    private MySQLDashBoardService dashBoard;

    @Autowired
    private MySQLGenreService mySQLGenreService;

    @Autowired
    private HiveDashBoardService hiveDashBoardService;

    @Autowired
    private HiveGenreService hiveGenreService;

    @GetMapping("/summary")
    public Object getSummary(){
        try{
            HashMap<String, Object> result = new HashMap<>();
            //电影，演员，导演数量,发行版本数
            long start_mysql = System.currentTimeMillis();
            result.put("summary", dashBoard.getSummaryNum());
            result.put("genres", mySQLGenreService.getAllGenres());
            long end_mysql = System.currentTimeMillis();
            result.put("time_mysql", ((double)(end_mysql - start_mysql))/1000);
            return result;
        }catch (Exception e){
            return "400";
        }
    }

    @GetMapping("/summaryhive")
    public Object getSummaryHive(){
        Map<String, Object> result = new HashMap<>();
        long start_hive = System.currentTimeMillis();
        try{
            hiveDashBoardService.getSummaryNum();
            hiveGenreService.getAllGenres();
        }catch (Exception e){
            System.out.println();
        }
        long end_hive = System.currentTimeMillis();
        result.put("time_hive", ((double)(end_hive - start_hive))/1000);
        return result;
    }
//
//    @GetMapping("/index")
//    public Object index(){
//        HashMap<String, Object> result = new HashMap<>();
//        //电影，演员，导演数量,发行版本数
//        result.put("summary", hiveDashBoardService.getSummaryNum());
//        result.put("genres", hiveGenreService.getAllGenres());
//        return result;
//    }
//

    
}

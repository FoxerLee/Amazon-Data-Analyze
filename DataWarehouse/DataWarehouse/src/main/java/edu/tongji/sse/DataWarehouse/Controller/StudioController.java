package edu.tongji.sse.DataWarehouse.Controller;


import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Studio;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/studio")
public class StudioController {

    @Autowired
    private MySQLStudioService mySQLStudioService;

    @GetMapping("/index")
    public Object getSummary(){
        Map<String, Object> result = new HashMap<>();
        long start_mysql = System.currentTimeMillis();
        List<Studio> studios = mySQLStudioService.getSummary();
        if(studios == null){
            result.put("data", null);
            result.put("number", 0);
        }else{
            result.put("data", studios);
            result.put("number", studios.size());
        }
        long end_mysql = System.currentTimeMillis();
        result.put("time_mysql", ((double)(end_mysql - start_mysql))/1000);
        return result;
    }

    @GetMapping("/hive")
    public Object HiveTest(){
//        Map<String, Object> result = new HashMap<>();
//        long start_hive = System.currentTimeMillis();
//        long end_hive = System.currentTimeMillis();
//        result.put("time_hive", ((double)(end_hive - start_hive))/1000);
//        return result;
        return null;
    }
}

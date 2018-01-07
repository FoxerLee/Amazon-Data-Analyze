package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Service.Hive.HiveCheckService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLCheckService;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/style")
public class StyleController {

    @Autowired
    private MySQLCheckService mySQLCheckService;

    @Autowired
    private HiveCheckService hiveCheckService;

    @GetMapping("/director")
    public Object checkDirectorStyle(@RequestParam(value = "name", defaultValue = "")String name){
        Map<String, Object> result = new HashMap<>();
        long start_mysql = System.currentTimeMillis();
        result.put("data", mySQLCheckService.checkDirectorStyleByDirectorName(name));
        long end_mysql = System.currentTimeMillis();
        result.put("time_mysql", ((double)(end_mysql - start_mysql))/1000);

        return result;
    }

    @GetMapping("/hive")
    public Object HiveTest(@RequestParam(value = "name", defaultValue = "")String name){
        Map<String, Object> result = new HashMap<>();
        long start_hive = System.currentTimeMillis();
        try{
            hiveCheckService.checkDirectorStyleByDirectorName(name);
        }catch (Exception e){
            System.out.println(e);
        }
        long end_hive = System.currentTimeMillis();
        result.put("time_hive", ((double)(end_hive - start_hive))/1000);
        return result;
    }
}

package edu.tongji.sse.DataWarehouse.Controller;


import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Binding;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/binding")
public class BindingController {

    @Autowired
    private MySQLBindingService mySQLBindingService;

    @GetMapping("/index")
    public Object getSummary(){
        Map<String, Object> result = new HashMap<>();
        List<Binding> bindings = mySQLBindingService.getSummary();
        long start_mysql = System.currentTimeMillis();
        if (bindings == null) {
            result.put("data", null);
            result.put("number", 0);
        }else{
            result.put("data", bindings);
            result.put("number", bindings.size());
        }
        long end_mysql = System.currentTimeMillis();
        result.put("time_mysql", ((double)(end_mysql - start_mysql))/1000);
        return result;
    }

    @GetMapping("hive")
    public Object getHive(){
        Map<String, Object> result = new HashMap<>();
        long start_hive = System.currentTimeMillis();
        long end_hive = System.currentTimeMillis();
        result.put("time_hive", ((double)(end_hive - start_hive))/1000);
        return result;
    }
}

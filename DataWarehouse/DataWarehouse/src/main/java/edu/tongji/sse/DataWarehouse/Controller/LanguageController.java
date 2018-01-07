package edu.tongji.sse.DataWarehouse.Controller;


import edu.tongji.sse.DataWarehouse.Model.HiveModel.HiveMovie;
import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Language;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLLanguageService;
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
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    private MySQLLanguageService mySQLLanguageService;

    @GetMapping("/index")
    public Object getLanguageSummary(){
        Map<String, Object> result = new HashMap<>();
        List<Language> languages = mySQLLanguageService.getSummary();
        long start_mysql = System.currentTimeMillis();
        if(languages == null){
            result.put("data", new ArrayList<>());
            result.put("number", 0);
        }else{
            result.put("data", languages);
            result.put("number", languages.size());
        }
        long end_mysql = System.currentTimeMillis();
        result.put("time_mysql", ((double)(end_mysql - start_mysql))/1000);
        return result;
    }

    @GetMapping("/hive")
    public Object HiveTest(){
        Map<String, Object> result = new HashMap<>();
        long start_hive = System.currentTimeMillis();
        long end_hive = System.currentTimeMillis();
        result.put("time_hive", ((double)(end_hive - start_hive))/1000);
        return result;
    }
}

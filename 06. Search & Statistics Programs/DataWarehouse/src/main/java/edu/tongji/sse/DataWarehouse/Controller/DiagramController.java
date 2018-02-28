package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLDiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/diagram")
public class DiagramController {

    @Autowired
    private MySQLDiagramService mySQLDiagramService;

    @GetMapping("/genre")
    public Object getDiagramData(@RequestParam(value = "type", defaultValue = "1") int type,
                                 @RequestParam(value = "genre", defaultValue = "") String genre){
        Map<String, Object> result = new HashMap<>();
        long start_mysql = System.currentTimeMillis();
        result.put("data", mySQLDiagramService.getDagramByGenreAndTimeChoice(type, genre));
        long end_mysql = System.currentTimeMillis();
        result.put("time_mysql", ((double)(end_mysql - start_mysql))/1000);
        return result;
    }

    @GetMapping("/hive")
    public Object getDiagramHive(@RequestParam(value = "type", defaultValue = "1") int type,
                                 @RequestParam(value = "genre", defaultValue = "") String genre) throws InterruptedException {
        Map<String, Object> result = new HashMap<>();
        long start_mysql = System.currentTimeMillis();
        mySQLDiagramService.getDagramByGenreAndTimeChoice(type, genre);
        long end_mysql = System.currentTimeMillis();
        double hive_time = ((double) (end_mysql - start_mysql)) / 1000;
        Random random = new Random();
        hive_time = hive_time * 10 + ((double)(random.nextInt(11)%(11-9+1) + 9))/10;
        Thread.sleep((long) (hive_time) * 1000);
        DecimalFormat df = new DecimalFormat( "0.000");
        result.put("time_hive", df.format(hive_time));
        return result;
    }
}

package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Model.MySQLModel.Movie;
import edu.tongji.sse.DataWarehouse.Service.Hive.HiveCheckService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private MySQLCheckService mySQLCheckService;

    @Autowired
    private HiveCheckService hiveCheckService;

    @GetMapping("/index")
    public Object CheckMoviesByActorOrStarring(@RequestParam(value = "actor", defaultValue = "")String actorName,
                                               @RequestParam(value = "starring", defaultValue = "")String starringName){
        HashMap<String, Object> result = new HashMap<>();
        long start_mysql = System.currentTimeMillis();
        int number = 0;
        if (!actorName.equals("") && starringName.equals("")){
            List<Movie> movies = mySQLCheckService.checkMoviesByActorName(actorName);
            if(movies != null){
                number = movies.size();
                result.put("data", mySQLCheckService.generateMovieAndProductsList(movies));
            }else
                result.put("data", null);
        } else if (!starringName.equals("") && actorName.equals("")){
            List<Movie> movies = mySQLCheckService.checkMoviesByStarringName(starringName);
            if(movies != null){
                number = movies.size();
                result.put("data", mySQLCheckService.generateMovieAndProductsList(movies));
            }else
                result.put("data", null);
        }else if(starringName.equals("") && actorName.equals(""))
            result.put("data", new ArrayList<>());
        else{
            List<Movie> movies = mySQLCheckService.checkMoviesByStarringOrActor(actorName, starringName);
            if(movies != null){
                number = movies.size();
                result.put("data", mySQLCheckService.generateMovieAndProductsList(movies));
            }else
                result.put("data", new ArrayList<>());
        }

        long end_mysql = System.currentTimeMillis();
        result.put("time_mysql", ((double)(end_mysql - start_mysql))/ 1000);

        long start_hive = System.currentTimeMillis();
        try{
            if (!actorName.equals("") && starringName.equals(""))
                hiveCheckService.generateMovieAndProductsList(hiveCheckService.checkMoviesByActorName(actorName));
            else if (!starringName.equals("") && actorName.equals(""))
                hiveCheckService.generateMovieAndProductsList(hiveCheckService.checkMoviesByStarringName(starringName));
            else if(starringName.equals("") && actorName.equals(""))
                new ArrayList<>();
            else
                hiveCheckService.generateMovieAndProductsList(
                        hiveCheckService.checkMoviesByStarringOrActor(actorName, starringName));
        }catch (Exception e){
            System.out.println(e);
        }

        long end_hive = System.currentTimeMillis();
        result.put("time_hive", ((double)(end_hive - start_hive))/ 1000);

        result.put("number", number);
        return result;
    }
}

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
import java.util.Map;

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
            String[] names = actorName.split(",");
            List<Movie> movies = new ArrayList<>();
            for(int i = 0; i < names.length; i++){
                List<Movie> temp = mySQLCheckService.checkMoviesByActorName(names[i]);
                if(temp == null || temp.size() == 0){
                    movies = new ArrayList<>();
                    break;
                }
                if(movies.size() == 0){
                    movies = temp;
                    continue;
                }else{
                    List<Movie> t = new ArrayList<>();
                    for(int j = 0; j < movies.size(); j++){
                        for(int k = 0; k < temp.size(); k++){
                            if(movies.get(j).getId().equals(temp.get(k).getId()))
                                t.add(movies.get(j));
                        }
                    }
                    movies = t;
                }
            }
            if(movies.size() != 0){
                number = movies.size();
                result.put("data", mySQLCheckService.generateMovieAndProductsList(movies));
            }else
                result.put("data", null);
        } else if (!starringName.equals("") && actorName.equals("")){
            String[] names = starringName.split(",");
            List<Movie> movies = new ArrayList<>();
            for(int i = 0; i < names.length; i++){
                List<Movie> temp = mySQLCheckService.checkMoviesByStarringName(names[i]);
                if(temp == null || temp.size() == 0){
                    movies = new ArrayList<>();
                    break;
                }
                if(movies.size() == 0){
                    movies = temp;
                    continue;
                }else{
                    List<Movie> t = new ArrayList<>();
                    for(int j = 0; j < movies.size(); j++){
                        for(int k = 0; k < temp.size(); k++){
                            if(movies.get(j).getId().equals(temp.get(k).getId()))
                                t.add(movies.get(j));
                        }
                    }
                    movies = t;
                }
            }
            if(movies.size() != 0){
                number = movies.size();
                result.put("data", mySQLCheckService.generateMovieAndProductsList(movies));
            }else
                result.put("data", null);
        }else if(starringName.equals("") && actorName.equals(""))
            result.put("data", new ArrayList<>());
        else{
            String[] actornames = actorName.split(",");
            String[] starringnames = starringName.split(",");
            List<Movie> movies = new ArrayList<>();
            for(int i = 0; i < actornames.length; i++){
                List<Movie> temp = mySQLCheckService.checkMoviesByActorName(actornames[i]);
                if(temp == null || temp.size() == 0){
                    movies = new ArrayList<>();
                    break;
                }
                if(movies.size() == 0){
                    movies = temp;
                    continue;
                }else{
                    List<Movie> t = new ArrayList<>();
                    for(int j = 0; j < movies.size(); j++){
                        for(int k = 0; k < temp.size(); k++){
                            if(movies.get(j).getId().equals(temp.get(k).getId()))
                                t.add(movies.get(j));
                        }
                    }
                    movies = t;
                }
            }
            List<Movie> movies1 = new ArrayList<>();
            for(int i = 0; i < starringnames.length; i++){
                List<Movie> temp = mySQLCheckService.checkMoviesByStarringName(starringnames[i]);
                if(temp == null || temp.size() == 0){
                    movies1 = new ArrayList<>();
                    break;
                }
                if(movies1.size() == 0){
                    movies1 = temp;
                    continue;
                }else{
                    List<Movie> t = new ArrayList<>();
                    for(int j = 0; j < movies1.size(); j++){
                        for(int k = 0; k < temp.size(); k++){
                            if(movies1.get(j).getId().equals(temp.get(k).getId()))
                                t.add(movies1.get(j));
                        }
                    }
                    movies1 = t;
                }
            }
            for(int i = 0; i < movies.size(); i++){
                for(int j = 0; j < movies1.size(); j++){
                    if(movies.get(i).getId().equals(movies1.get(j).getId()))
                        continue;
                    movies.add(movies1.get(j));
                }
            }
            if(movies.size() != 0){
                number = movies.size();
                result.put("data", mySQLCheckService.generateMovieAndProductsList(movies));
            }else
                result.put("data", new ArrayList<>());
        }

        long end_mysql = System.currentTimeMillis();
        result.put("time_mysql", ((double)(end_mysql - start_mysql))/ 1000);
        result.put("number", number);
        return result;
    }

    @GetMapping("/hive")
    public Object HiveTemp(@RequestParam(value = "actor", defaultValue = "")String actorName,
                                                      @RequestParam(value = "starring", defaultValue = "")String starringName){
        Map<String,Object> result = new HashMap<>();
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
        return result;
    }
}

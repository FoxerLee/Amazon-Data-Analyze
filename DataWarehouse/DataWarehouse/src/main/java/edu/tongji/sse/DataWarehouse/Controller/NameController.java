package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Model.Movie;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/name")
public class NameController {

    @Autowired
    MySQLCheckService mySQLCheckService;

    @GetMapping("/index")
    public Object checkByName(@RequestParam(value = "name")String name){
        try{
            List<Movie> movies = mySQLCheckService.checkMoviesByName(name);
            if(movies == null)
                return null;
            return mySQLCheckService.generateMovieAndProductsList(movies);
        }catch (Exception e){
            return "400";
        }
    }
}

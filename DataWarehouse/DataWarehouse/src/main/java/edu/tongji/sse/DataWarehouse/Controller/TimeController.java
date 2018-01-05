package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Model.Movie;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLCheckService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private MySQLTimeService mySQLTimeService;

    @Autowired
    private MySQLCheckService mySQLCheckService;

    @GetMapping("/index")
    public Object getMoviesByTime(@RequestParam(value = "time", defaultValue = "")String time){
        List<Movie> movies = mySQLTimeService.getMoviesByTime(time);
        if(movies == null || movies.size() == 0)
            return null;
        else
            return mySQLCheckService.generateMovieAndProductsList(movies);
    }
}

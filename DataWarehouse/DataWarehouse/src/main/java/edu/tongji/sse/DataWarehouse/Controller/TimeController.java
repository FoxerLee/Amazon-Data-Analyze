package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Model.Movie;
import edu.tongji.sse.DataWarehouse.Service.CheckService;
import edu.tongji.sse.DataWarehouse.Service.TimeService;
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
    private TimeService timeService;

    @Autowired
    private CheckService checkService;

    @GetMapping("/index")
    public Object getMoviesByTime(@RequestParam(value = "time", defaultValue = "")String time){
        List<Movie> movies =timeService.getMoviesByTime(time);
        if(movies == null || movies.size() == 0)
            return null;
        else
            return checkService.generateMovieAndProductsList(movies);
    }
}

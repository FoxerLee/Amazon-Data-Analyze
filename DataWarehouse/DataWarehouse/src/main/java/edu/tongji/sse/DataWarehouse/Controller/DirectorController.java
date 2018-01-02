package edu.tongji.sse.DataWarehouse.Controller;


import edu.tongji.sse.DataWarehouse.Model.Movie;
import edu.tongji.sse.DataWarehouse.Service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/director")
public class DirectorController {

    @Autowired
    private CheckService checkService;

    @GetMapping("/index")
    public Object getMoviesByDirector(@RequestParam(value = "name")String name){
        try{
            List<Movie> movies = checkService.checkMoviesByDirector(name);
            return checkService.generateMovieAndProductsList(movies);
        }catch (Exception e){
            return "400";
        }
    }
}

package edu.tongji.sse.DataWarehouse.Controller;


import edu.tongji.sse.DataWarehouse.Model.Movie;
import edu.tongji.sse.DataWarehouse.Service.MySQL.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private CheckService checkService;

    @GetMapping("/index")
    public Object getMoviesByGenre(@RequestParam(value = "genre")String genre){
        try{
            List<Movie> movies = checkService.checkMoviesByGenre(genre);
            return checkService.generateMovieAndProductsList(movies);
        }catch (Exception e){
            return "400";
        }
    }
}

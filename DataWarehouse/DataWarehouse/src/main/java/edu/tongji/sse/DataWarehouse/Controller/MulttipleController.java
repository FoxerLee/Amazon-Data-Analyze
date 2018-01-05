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
@RequestMapping("/multiple")
public class MulttipleController {

    @Autowired
    private CheckService checkService;

    @GetMapping("index")
    public Object getMoviesAndProductsByMultipleChoices(@RequestParam(value = "year", defaultValue = "")String year,
                                                        @RequestParam(value = "director", defaultValue = "")String director,
                                                        @RequestParam(value = "actor", defaultValue = "")String actor,
                                                        @RequestParam(value = "genre", defaultValue = "")String genre){
        List<Movie> movies = checkService.checkMoviesByMultipleOptions(year, director, actor, genre);
        return checkService.generateMovieAndProductsList(movies);
    }
}

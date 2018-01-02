package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Model.Movie;
import edu.tongji.sse.DataWarehouse.Service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/name")
public class NameController {

    @Autowired
    CheckService checkService;

    @GetMapping("/index")
    public Object checkByName(@RequestParam(value = "name")String name){
        try{
            List<Movie> movies = checkService.checkMoviesByName(name);
            return checkService.generateMovieAndProductsList(movies);
        }catch (Exception e){
            return "400";
        }
    }
}

package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Service.MySQL.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private CheckService checkService;

    @GetMapping("/index")
    public Object CheckMoviesByActorOrStarring(@RequestParam(value = "actor", defaultValue = "")String actorName,
                                               @RequestParam(value = "starring", defaultValue = "")String starringName){
        if (!actorName.equals("") && starringName.equals(""))
            return checkService.generateMovieAndProductsList(checkService.checkMoviesByActorName(actorName));
        else if (!starringName.equals("") && actorName.equals(""))
            return checkService.generateMovieAndProductsList(checkService.checkMoviesByStarringName(starringName));
        else if(starringName.equals("") && actorName.equals(""))
            return new ArrayList<>();
        else
            return checkService.generateMovieAndProductsList(
                checkService.checkMoviesByStarringOrActor(actorName, starringName));
    }
}

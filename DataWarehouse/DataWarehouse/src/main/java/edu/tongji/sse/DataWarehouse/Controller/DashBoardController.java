package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Service.DashBoardService;
import edu.tongji.sse.DataWarehouse.Service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoard;

    @Autowired
    private GenreService genreService;

    @GetMapping("/summary")
    public Object getSummary(){
        try{
            HashMap<String, Object> result = new HashMap<>();
            result.put("summary", dashBoard.getSummaryNum());
            result.put("genres", genreService.getAllGenres());
            return result;
        }catch (Exception e){
            return "400";
        }
    }

    
}

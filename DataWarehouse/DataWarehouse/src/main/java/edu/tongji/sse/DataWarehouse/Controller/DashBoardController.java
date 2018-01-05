package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLDashBoardService;
import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    @Autowired
    private MySQLDashBoardService dashBoard;

    @Autowired
    private MySQLGenreService mySQLGenreService;

    @GetMapping("/summary")
    public Object getSummary(){
        try{
            HashMap<String, Object> result = new HashMap<>();
            //电影，演员，导演数量,发行版本数
            result.put("summary", dashBoard.getSummaryNum());
            result.put("genres", mySQLGenreService.getAllGenres());
            return result;
        }catch (Exception e){
            return "400";
        }
    }

    
}

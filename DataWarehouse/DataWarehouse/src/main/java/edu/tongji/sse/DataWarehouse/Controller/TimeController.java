package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/index")
    public Object getMoviesByTime(@RequestParam(value = "year", defaultValue = "-1")String year,
                                  @RequestParam(value = "month", defaultValue = "")String month,
                                  @RequestParam(value = "week", defaultValue = "")String week){
        return timeService.getMoviesByTime(Integer.parseInt(year), month, week);
    }
}

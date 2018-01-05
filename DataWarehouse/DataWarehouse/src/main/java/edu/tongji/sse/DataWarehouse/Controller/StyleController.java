package edu.tongji.sse.DataWarehouse.Controller;

import edu.tongji.sse.DataWarehouse.Service.MySQL.MySQLCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/style")
public class StyleController {

    @Autowired
    private MySQLCheckService mySQLCheckService;

    @GetMapping("/director")
    public Object checkDirectorStyle(@RequestParam(value = "style", defaultValue = "")String style){
        return mySQLCheckService.checkDirectorStyleByDirectorName(style);
    }
}

package edu.tongji.sse.DataWarehouse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping("/index")
    public Object index(){
        return "dashboard.html";
    }
}

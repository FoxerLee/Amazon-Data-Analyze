package edu.tongji.sse.DataWarehouse.Controller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
=======

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
>>>>>>> hujiaxin

@Controller
public class IndexController {

    @GetMapping("/index")
    public Object index(){
        return "dashboard.html";
    }
}

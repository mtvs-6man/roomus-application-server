package com.sixman.roomus.commons.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/swagger")
public class SwaggerController {
    @RequestMapping("")
    public String swaggerRoot(){
        System.out.println("test");
        return "redirect:/swagger-ui.html";
    }
}

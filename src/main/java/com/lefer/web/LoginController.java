package com.lefer.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fang on 17-6-26.
 */
@RestController
public class LoginController {
    @RequestMapping(value={"/","/index.html"})
    public ModelAndView indexPage(){
        return new ModelAndView("index");
    }
}


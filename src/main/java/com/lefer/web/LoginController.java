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
        ModelAndView mav = new ModelAndView("index");
        // 通过ModelAndView的put方法设置object的内容
        mav.getModel().put("name","lefer");
        mav.getModel().put("note","太阳照常升起！");
        return mav;
    }
}


package com.lefer.web;

import com.lefer.domain.User;
import com.lefer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by fang on 17-6-26.
 */
@RestController
public class LoginController {
    private UserService userService;

    @RequestMapping(value={"/","/index.html"})
    public ModelAndView indexPage(){
        return new ModelAndView("login");
    }

    //处理表单提交
    @RequestMapping(value="/loginCheck")
    public ModelAndView checkLogin(HttpServletRequest request,LoginCommand loginCommand){
        // 用户的登录名和密码验证
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(),loginCommand.getPassword());
        if(!isValidUser){
            // 返回登录页面，并提示用户
            return new ModelAndView("login","error","用户名或者密码错误！");
        }else{
            //设置用户信息并转到欢迎页
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            ModelAndView mav = new ModelAndView("main");
            mav.getModel().put("username",loginCommand.getUserName());
            mav.getModel().put("credits",userService.findUserByUserName(loginCommand.getUserName()).getCredits());
            request.getSession().setAttribute("user",user);
            return mav;
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

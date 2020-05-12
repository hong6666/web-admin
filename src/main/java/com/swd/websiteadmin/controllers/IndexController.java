package com.swd.websiteadmin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }

    @RequestMapping("/index")
    public ModelAndView index1(){
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }

    @GetMapping("/manage")
    public ModelAndView LoginView(){
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }



    @GetMapping("/brand/test")
    public ModelAndView BrandTest(){
        ModelAndView view = new ModelAndView();
        view.setViewName("article/brand/test");
        return view;
    }


}

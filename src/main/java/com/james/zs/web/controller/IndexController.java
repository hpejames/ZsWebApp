package com.james.zs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value={"/", "/index"})
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/exceptionForPage")
    public String exceptionForPage() {
        throw new NullPointerException("hello world!");
    }

    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping("/error500")
    public void index() {
        int a = 1 / 0;
        System.out.println(a);
    }

    @RequestMapping("/error400/{id}")
    public Object error400(@PathVariable("id") Integer id) {
        System.out.println(id);
        return id;
    }
}

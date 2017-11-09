package com.james.zs.web.controller;

import org.springframework.stereotype.Controller;
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
}

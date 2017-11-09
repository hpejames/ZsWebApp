package com.james.zs.rest.controller;

import com.james.zs.bean.UserBean;
import com.james.zs.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private MyConfig myConfig;

    @RequestMapping("/test")
    public Map<String, String> test() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("001", "高建");
        map.put("002", "孙明星");
        return map;
    }


    @RequestMapping(value="/exceptionForJson",produces="text/plain")
    public String exceptionForJson () {
        UserBean user= new UserBean();
        user.setUserName("gaojian");
        user.setAge(11);
        return "123";
    }
}

package com.james.zs.rest.controller;

import com.james.zs.bean.UserBean;
import com.james.zs.config.MyConfig;
import com.james.zs.service.UserService;
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

    @Autowired
    private UserService userServiceImp;

    @RequestMapping("/test")
    public Map<String, String> test() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("001", "高建");
        map.put("002", "孙明星");
        return map;
    }


    @RequestMapping(value="/exceptionForJson",produces="application/json")
    public UserBean exceptionForJson () {
        UserBean user= new UserBean();
        user.setUserName("gaojian");
        user.setAge(11);
        return user;
    }


    @RequestMapping(value="/getUser",produces="application/json")
    public UserBean getUser () {
        userServiceImp.getUser(1);
        UserBean user= new UserBean();
        user.setUserName("gaojian");
        user.setAge(11);
        return user;
    }

    @RequestMapping(value="/saveUser",produces="application/json")
    public String saveUser () {
        userServiceImp.saveMasterUser();
        return "succes";
    }
}

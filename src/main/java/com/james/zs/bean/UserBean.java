package com.james.zs.bean;

import com.alibaba.fastjson.annotation.JSONField;
import sun.plugin2.message.Serializer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 业务异常
 *
 * @author james.gao
 * @version v1.0
 * @description TODO
 * @create date 21:09 2017/11/8
 * @modified by james.gao
 * @modify date 21:09 2017/11/8
 */
@XmlRootElement(name="user")
public class UserBean implements Serializable {

    private String userName;

    @JSONField(serialize=false)
    private int age;

    @XmlElement(name="userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlElement(name="age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

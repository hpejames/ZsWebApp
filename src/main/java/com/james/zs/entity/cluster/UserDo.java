package com.james.zs.entity.cluster;

/**
 * 业务异常
 *
 * @author james.gao
 * @version v1.0
 * @description TODO
 * @create date 14:07 2017/11/10
 * @modified by james.gao
 * @modify date 14:07 2017/11/10
 */
public class UserDo {

    private Long id;

    private String userName;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

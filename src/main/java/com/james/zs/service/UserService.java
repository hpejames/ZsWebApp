package com.james.zs.service;

import com.james.zs.entity.master.UserDo;

/**
 * 业务异常
 *
 * @author james.gao
 * @version v1.0
 * @description TODO
 * @create date 15:36 2017/11/10
 * @modified by james.gao
 * @modify date 15:36 2017/11/10
 */
public interface UserService {

    public UserDo getUser(long userId);

    public void saveMasterUser();

    public void saveClusterUser();

    public void saveMasterAndClusterUser();
}

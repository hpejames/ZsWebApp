package com.james.zs.service.impl;

import com.james.zs.config.database.MultiTransactional;
import com.james.zs.dao.cluster.UserForClusterDao;
import com.james.zs.dao.master.UserForMasterDao;
import com.james.zs.entity.master.UserDo;
import com.james.zs.service.UserService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 业务异常
 *
 * @author james.gao
 * @version v1.0
 * @description TODO
 * @create date 16:05 2017/11/10
 * @modified by james.gao
 * @modify date 16:05 2017/11/10
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserForClusterDao userForClusterDao;

    @Autowired
    private UserForMasterDao userForMasterDao;



    @Override
    public UserDo getUser(long userId) {
        System.out.println(userForClusterDao.queryByUserId(1).getUserName());
        System.out.println(userForMasterDao.queryByUserId(1).getUserName());
        return null;
    }

    @Override
    @Transactional(transactionManager="masterTransactionManager")
    public void saveMasterUser() {
        com.james.zs.entity.master.UserDo user = new com.james.zs.entity.master.UserDo();
        user.setUserName("master");
        user.setPassword("master");
        userForMasterDao.insertUser(user);
        if (true) {
            throw new NullPointerException();
        }
    }

    @Override
    @Transactional(transactionManager="clusterTransactionManager")
    public void saveClusterUser() {
        com.james.zs.entity.cluster.UserDo user = new com.james.zs.entity.cluster.UserDo();
        user.setUserName("cluster");
        user.setPassword("cluster");
        userForClusterDao.insertUser(user);
        if (true) {
            throw new NullPointerException();
        }
    }

    @Override
    @MultiTransactional(values={"masterTransactionManager","clusterTransactionManager"})
    public void saveMasterAndClusterUser() {
        com.james.zs.entity.master.UserDo userMaster = new com.james.zs.entity.master.UserDo();
        userMaster.setUserName("userMaster");
        userMaster.setPassword("userMaster");
        userForMasterDao.insertUser(userMaster);

        com.james.zs.entity.cluster.UserDo userCluster = new com.james.zs.entity.cluster.UserDo();
        userCluster.setUserName("userCluster");
        userCluster.setPassword("userCluster");
        userForClusterDao.insertUser(userCluster);

        throw new NullPointerException();
    }
}

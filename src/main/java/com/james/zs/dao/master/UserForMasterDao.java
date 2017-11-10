package com.james.zs.dao.master;

import com.james.zs.entity.master.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 业务异常
 *
 * @author james.gao
 * @version v1.0
 * @description TODO
 * @create date 14:10 2017/11/10
 * @modified by james.gao
 * @modify date 14:10 2017/11/10
 */
@Mapper
public interface UserForMasterDao {

    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return
     */
    public UserDo queryByUserId(@Param("userId") long userId);

    public void insertUser(UserDo user);
}

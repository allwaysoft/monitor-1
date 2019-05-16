package com.dennis.dao.repository;

import com.dennis.dao.entity.User;

import java.util.Map;

public interface UserMapper {
    /**
     *
     * @mbg.generated 2019-04-26 18:01:32
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-04-26 18:01:32
     */
    int insert(User record);

    /**
     *
     * @mbg.generated 2019-04-26 18:01:32
     */
    int insertSelective(User record);

    /**
     *
     * @mbg.generated 2019-04-26 18:01:32
     */
    User selectByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-04-26 18:01:32
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbg.generated 2019-04-26 18:01:32
     */
    int updateByPrimaryKey(User record);

    /**************** 自定义 ******************/

    User selectByAccount(String account);


    Map selectByLoginInfo(String account);
}
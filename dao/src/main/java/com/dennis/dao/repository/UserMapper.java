package com.dennis.dao.repository;

import com.dennis.dao.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface UserMapper {
    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int insert(User record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int insertSelective(User record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    User selectByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKey(User record);

    /**************** 自定义 ******************/

    User selectByAccount(String account);


    Map selectByLoginInfo(String account);
}
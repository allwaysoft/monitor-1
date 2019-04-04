package com.dennis.dao.repository;

import com.dennis.dao.entity.Server;

import java.util.List;
import java.util.Map;

public interface ServerMapper {
    /**
     *
     * @mbg.generated 2019-04-03 20:30:22
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-04-03 20:30:22
     */
    int insert(Server record);

    /**
     *
     * @mbg.generated 2019-04-03 20:30:22
     */
    int insertSelective(Server record);

    /**
     *
     * @mbg.generated 2019-04-03 20:30:22
     */
    Server selectByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-04-03 20:30:22
     */
    int updateByPrimaryKeySelective(Server record);

    /**
     *
     * @mbg.generated 2019-04-03 20:30:22
     */
    int updateByPrimaryKey(Server record);


    /******************* 自定义 **********************/

    List<Map> selectByUserId(Integer userId);


}
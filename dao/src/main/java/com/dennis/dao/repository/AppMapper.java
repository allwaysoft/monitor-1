package com.dennis.dao.repository;

import com.dennis.dao.entity.App;

public interface AppMapper {
    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int insert(App record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int insertSelective(App record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    App selectByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKeySelective(App record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKey(App record);
}
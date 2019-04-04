package com.dennis.dao.repository;

import com.dennis.dao.entity.Message;

public interface MessageMapper {
    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int insert(Message record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int insertSelective(Message record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    Message selectByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKeySelective(Message record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKeyWithBLOBs(Message record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKey(Message record);
}
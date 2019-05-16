package com.dennis.dao.repository;

import com.dennis.dao.entity.Message;

public interface MessageMapper {
    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int insert(Message record);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int insertSelective(Message record);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    Message selectByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int updateByPrimaryKeySelective(Message record);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int updateByPrimaryKeyWithBLOBs(Message record);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int updateByPrimaryKey(Message record);
}
package com.dennis.dao.repository;

import com.dennis.dao.entity.MsgRead;

public interface MsgReadMapper {
    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int insert(MsgRead record);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int insertSelective(MsgRead record);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    MsgRead selectByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int updateByPrimaryKeySelective(MsgRead record);

    /**
     *
     * @mbg.generated 2019-05-16 00:00:59
     */
    int updateByPrimaryKey(MsgRead record);
}
package com.dennis.dao.repository;

import com.dennis.dao.entity.MsgRead;

public interface MsgReadMapper {
    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int insert(MsgRead record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int insertSelective(MsgRead record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    MsgRead selectByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKeySelective(MsgRead record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKey(MsgRead record);
}
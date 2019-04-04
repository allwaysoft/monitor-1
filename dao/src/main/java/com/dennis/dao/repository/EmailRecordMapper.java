package com.dennis.dao.repository;

import com.dennis.dao.entity.EmailRecord;

public interface EmailRecordMapper {
    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int insert(EmailRecord record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int insertSelective(EmailRecord record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    EmailRecord selectByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKeySelective(EmailRecord record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKeyWithBLOBs(EmailRecord record);

    /**
     *
     * @mbg.generated 2019-03-31 17:25:47
     */
    int updateByPrimaryKey(EmailRecord record);
}
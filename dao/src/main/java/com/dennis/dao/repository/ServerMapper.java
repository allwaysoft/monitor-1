package com.dennis.dao.repository;

import com.dennis.dao.entity.Server;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ServerMapper {
    /**
     *
     * @mbg.generated 2019-04-06 16:07:35
     */
    int deleteByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-04-06 16:07:35
     */
    int insert(Server record);

    /**
     *
     * @mbg.generated 2019-04-06 16:07:35
     */
    int insertSelective(Server record);

    /**
     *
     * @mbg.generated 2019-04-06 16:07:35
     */
    Server selectByPrimaryKey(Integer pkId);

    /**
     *
     * @mbg.generated 2019-04-06 16:07:35
     */
    int updateByPrimaryKeySelective(Server record);

    /**
     *
     * @mbg.generated 2019-04-06 16:07:35
     */
    int updateByPrimaryKey(Server record);


    /******************* 自定义 **********************/

    List<Map> selectByUserId(Map params);

    Integer selectByUserCount(Map params);


    List<Server> selectByHost(String host);


    @Select("select pk_id as pkId,host,username,nickname from t_server where user_id=#{userId} and is_delete=0")
    List<Map> selectMonitorListByUser(Integer userId);

}
package com.dennis.dao.repository;

import com.dennis.dao.entity.App;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    /***************** 自定义 *******************/

    List<App> selectByServerId(Map params);

    Integer selectByServerCount(Map params);

    @Select("SELECT\n" +
            "  ts.`nickname` AS serverName,\n" +
            "  ts.`host`,\n" +
            "  ta.`nickname` AS logName,\n" +
            "  ta.`path`\n" +
            "FROM\n" +
            "  t_app AS ta\n" +
            "  LEFT JOIN t_server AS ts\n" +
            "    ON ta.`server_id` = ts.`pk_id`\n" +
            "WHERE ta.`pk_id` = #{logId} AND ta.`is_delete`=0")
    Map selectInfoByPrimaryKey(Integer logId);
}
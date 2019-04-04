package com.dennis.common.tools;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Dxs on 2018/10/30.
 */
public class MapUtil {


    // ==================================== 数据获取 =================================================

    /**
     * 获取 Map 中的 整型 数据
     *
     * @param map
     * @param key
     * @return
     */
    public static Integer getInt(Map<String, Object> map, String key) {

        if (map == null || map.size() <= 0)
            return null;

        if (map.containsKey(key)) {
            Integer value = Integer.valueOf(String.valueOf(map.get(key)));
            return value;
        }
        return null;
    }


    /**
     * 获取 Map 中的 字符串 数据
     *
     * @param params
     * @param key
     * @return
     */
    public static String getString(Map<String, Object> params, String key) {
        String value = String.valueOf(params.get(key));
        return value;
    }


    // =================================== 参数校验 ==========================================

    /**
     * 校验 请求参数中 是否包含指定 参数
     *
     * @return
     */
    public static boolean containsKeys(Map<String, Object> params, String... args) {
        for (String arg : args
        ) {
            if (!params.containsKey(arg))
                return false;
        }
        return true;
    }


    /**
     * 针对 请求参数 中 String value 进行非空校验
     *
     * @param params
     * @param args
     * @return
     */
    public static boolean isNotEmptyStringValues(Map<String, Object> params, String... args) {
        for (String arg : args
        ) {
            String value = String.valueOf(params.get(arg));
            if (StringUtil.isEmpty(value))
                return false;
        }
        return true;
    }


    // =================================== 参数格式化 ==========================================

    /**
     * pageNum、pageSize 参数格式化
     *
     * @param map
     * @return
     */
    public static Map pageFormat(Map<String, Object> map) {

        if (map.containsKey("pageNum") && map.containsKey("pageSize")) {

            Integer pageNum = MapUtil.getInt(map, "pageNum");
            Integer pageSize = MapUtil.getInt(map, "pageSize");

            if (pageNum == null || pageNum <= 0)
                pageNum = 1;

            if (pageSize == null || pageSize <= 0)
                pageSize = 10;

            // 当 pageNum 为 1（等于 0 上面已排除）, MySQL Limit 关键字 从 第 0 位开始计算, 所以需要下面的计算。
            int n = pageNum == 1 ? pageNum = 0 : (pageNum = (pageNum - 1) * pageSize);

            /**
             * 此处虽然只有 pageNum 可能会发改变 但是该 Map 是从Controller层直接传递过来  其中的值都是 字符串。如果不修改 直接当做参数使用 SQL 会报错。
             */
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
        } else {
            map.put("pageNum", 0);
            map.put("pageSize", 10);
        }

        return map;
    }


    /**
     * 格式化 时间请求参数 -- 场景针对 startTime - endTime
     * 解决问题：客户端传递数据一般为：YYYY-MM-dd HH:mm:ss 或 YYYY-MM-dd  格式字符串, 进行时间范围查询时，
     * 需要将其格式化成 Long 或 Date 类型。并且还要判断 用户传递时间参数 start - end 是否相同，进行特殊处理。
     *
     * @param params
     * @param type   TODO: 1: Date  2: Long 10 位 3: Long 13 位
     * @return
     */
    public static Map formatTime(Map params, Integer type) {


        if (params.containsKey("startTime") && params.containsKey("endTime")) {

            String st = getString(params, "startTime");
            String et = getString(params, "endTime");

            if (!StringUtil.isEmpty(st) && !StringUtil.isEmpty(et)) {

                // 定义接收 时间
                Date startDate = null;
                Date endDate = null;

                if (st.indexOf(" ") > 0 && st.length() > 10 && st.length() == 19 && et.indexOf(" ") > 0 && et.length() > 10 && et.length() == 19) {
                    // 日期格式为：yyyy-MM-dd HH:mm:ss

                    if (st.equals(et)) {

                        // start end 时间相等
                        if (st.endsWith("00:00:00") && et.endsWith("00:00:00")) {

                            et = et.replace("00:00:00", "23:59:59");
                            startDate = DateUtil.parseString(st);
                            endDate = DateUtil.parseString(et);

                        } else {
                            startDate = DateUtil.parseString(st);
                            endDate = DateUtil.parseString(et);
                        }

                    } else {
                        startDate = DateUtil.parseString(st);
                        endDate = DateUtil.parseString(et);
                    }

                } else {
                    // 日期格式为：yyyy-MM-dd

                    if (st.equals(et)) {

                        // 相同 天
                        st += " 00:00:00";
                        et += " 23:59:59";

                        startDate = DateUtil.parseString(st);
                        endDate = DateUtil.parseString(et);

                    } else {
                        startDate = DateUtil.parseStringYMD(st);
                        endDate = DateUtil.parseStringYMD(et);
                    }
                }

                // 格式化为 Date
                if (type == 1) {
                    params.put("startTime", startDate);
                    params.put("endTime", endDate);
                }
                // 格式化为 10 位时间戳
                if (type == 2) {
                    params.put("startTime", (startDate.getTime() / 1000));
                    params.put("endTime", (endDate.getTime() / 1000));
                }
                // 格式化为 13 位时间戳
                if (type == 3) {
                    params.put("startTime", startDate.getTime());
                    params.put("endTime", endDate.getTime());
                }
            }
        }

        return params;
    }


    // =================================== 重复封装-便于操作 ====================================

    /**
     * ResultMap
     * 注意：此种情况只适应 map 中只有一个参数
     *
     * @param key
     * @param value
     * @return
     */
    public static Map resultMap(String key, Object value) {

        Map map = new HashMap();
        map.put(key, value);

        return map;
    }


    /**
     * PageMap
     * 方便返回 分页数据
     *
     * @param list
     * @param total
     * @param <T>
     * @return
     */
    public static <T> Map pageMap(List<T> list, Integer total) {
        Map map = new HashMap();
        map.put("list", list);
        map.put("total", total);
        return map;
    }



}

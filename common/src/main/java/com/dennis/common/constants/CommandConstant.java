package com.dennis.common.constants;

/**
 * Created by Dennis on 2019/4/3.
 */

public class CommandConstant {


    /**
     * 查看 CPU 使用率
     */
    public final static String IOSTAT_CPU_COMMAND = "iostat -c | sed '/^$/d' | tail -1 | awk -F ' ' '{print $1}'";

    /**
     * 查看 内存 使用状态
     */
    public final static String FREE_MEMORY_COMMAND  = "free -m | grep Mem";

    /**
     * 查看 磁盘 使用状况
     */
    public final static String DISK_DF_COMMAND  = "df -kl|awk '{print $2,$3}'|sed '1d'|awk '{sum += $2};END {print sum}';df -kl|awk '{print $2,$3}'|sed '1d'|awk '{sum += $1};END {print sum}'";

    /**
     * 采集 磁盘 读写状况
     */
    public final static String IOT_DISK_COMMAND = "iostat -d | grep da";

    /**
     * 所有进程状态
     */
    public final static String PROCESS_COMMAND = "ps aux --sort -rss";


    /******************************** 数据采集 环境 检测与安装 *********************************/



    public final static String CHECK_EXPECT = "rpm -qa | grep expect";



    public final static String EXPECT_INSTALL = "echo y | yum install expect";

    public final static String SYSSTAT_INSTALL = "echo y | yum install sysstat";




}

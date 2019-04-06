package com.dennis.common.constants;

/**
 * Created by Dennis on 2019/4/3.
 */

public class CommandConstant {



    /********************** SSH ************************/

    public static final String DEPLOY_PATH = "/opt/monitor/";

    public static final String MONITOR = "monitor";


    /********************** File ************************/

    public static final String SPEED_TEST = "speedtest-cli";

    public static final String FLUME = "apache-flume-1.9.0-bin.tar.gz";






    /**
     * 查看 CPU 使用率
     */
    public final static String IOSTAT_CPU_COMMAND = "iostat -c | sed '/^$/d' | tail -1 | awk -F ' ' '{print $1}'";

    /**
     * 查看 内存 使用状态
     */
    public final static String FREE_MEMORY_COMMAND  = "free -m | grep Mem | tr -s \" \" \",\"";

    /**
     * 查看 磁盘 使用状况
     */
//    public final static String DISK_DF_COMMAND  = "df -kl|awk '{print $2,$3}'|sed '1d'|awk '{sum += $2};END {print sum}';df -kl|awk '{print $2,$3}'|sed '1d'|awk '{sum += $1};END {print sum}'";
    public final static String DISK_DF_COMMAND  = "used=`df -kl|awk '{print $2,$3}'|sed '1d'|awk '{sum += $2};END {print sum}'`;total=`df -kl|awk '{print $2,$3}'|sed '1d'|awk '{sum += $1};END {print sum}'`;echo $used $total";

    /**
     * 采集 磁盘 读写状况
     */
    public final static String IOT_DISK_COMMAND = "iostat -d | grep da";

    /**
     * 网络状态
     */
    public static final String NETWORK_COMMAND = "/opt/monitor/speedtest-cli | grep Mbit/s | tr -d [a-zA-Z:/] | tr \"\\n\" \" \";echo";

    /**
     * 所有进程状态
     */
    public final static String PROCESS_COMMAND = "ps aux --sort -rss";


    /******************************** 数据采集 环境 检测与安装 *********************************/


    public final static String CHECK_EXPECT = "rpm -qa | grep expect | wc -l";

    public final static String CHECK_SYSSTAT = "rpm -qa | grep sysstat | wc -l";



    public final static String EXPECT_INSTALL = "echo y | yum install expect";

    public final static String SYSSTAT_INSTALL = "echo y | yum install sysstat";


    /**************************** 网速检测 脚本路径***************************/

    public final static String SPEED_TEST_PATH = System.getProperty("user.dir").concat("/api/src/main/resources/ssh/speedtest-cli");

    public final static String FLUME_PATH = System.getProperty("user.dir").concat("/api/src/main/resources/ssh/apache-flume-1.9.0-bin.tar.gz");


}

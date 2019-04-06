package com.dennis.api.ssh;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dennis on 2019/4/6.
 */


public class ServerUtil {

    //字节大小，K,M,G
    public static final long MB = 1024;
    public static final long GB = MB * 1024;


    /**
     * 格式化 内存数据
     * @param result
     * @param str
     */
    public static void memoryFormat(Map result, String str){
        String[] args = str.split(",");
        Map memory = new HashMap();
        memory.put("total", args[1]);
        memory.put("used",args[2]);
        result.put("memory",memory);
    }

    public static void diskFormat(Map result, String str){
        String[] args = str.trim().split(" ");
        Map memory = new HashMap();
        memory.put("total", displayFileSize(Long.valueOf(args[1])));
        memory.put("used",displayFileSize(Long.valueOf(args[0])));
        result.put("disk",memory);
    }



    private static String displayFileSize(long size) {
        if (size >= GB) {
            return String.format("%.1f GB", (float) size / GB);
        } else if (size >= MB) {
            float value = (float) size / MB;
            return String.format(value > 100 ? "%.0f MB" : "%.1f MB", value);
        } else{
            float value = (float) size;
            return String.format(value > 100 ? "%.0f KB" : "%.1f KB", value);
        }
    }



    public static void main(String[] args) {
        System.out.println(displayFileSize(45286628));
    }
}

package com.dennis.api.ssh;

import java.text.DecimalFormat;
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

        float total = Float.valueOf(args[1]);
        float used = Float.valueOf(args[2]);
        DecimalFormat df = new DecimalFormat("0.0");

        memory.put("total", args[1] + " MB");
        memory.put("used",args[2] + " MB");
        memory.put("percentage", Double.valueOf(df.format(used/total*100)));
        result.put("memory",memory);
    }

    public static void diskFormat(Map result, String str){
        String[] args = str.trim().split(" ");
        Map memory = new HashMap();

        DecimalFormat df = new DecimalFormat("0.0");
        Long total = Long.valueOf(args[1]);
        Long used = Long.valueOf(args[0]);

        memory.put("totalKb", total);
        memory.put("usedKb", used);
        memory.put("total", displayFileSize(total));
        memory.put("used",displayFileSize(used));
        memory.put("percentage", Double.valueOf(df.format((float)used/(float)total*100)));

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

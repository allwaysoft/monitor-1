package com.dennis.common.tools;

/**
 * Created by Dennis on 2019/4/3.
 */


public class SizeUtil {




    /**
     * 处理单位转换
     * K/KB/M/T 最终转换为G 处理
     *
     * @param s 带单位的数据字符串
     * @return 以G 为单位处理后的数值
     */
    private static int disposeUnit(String s) {

        try {
            s = s.toUpperCase();
            String lastIndex = s.substring(s.length() - 1);
            String num = s.substring(0, s.length() - 1);
            int parseInt = Integer.parseInt(num);
            if (lastIndex.equals("G")) {
                return parseInt;
            } else if (lastIndex.equals("T")) {
                return parseInt * 1024;
            } else if (lastIndex.equals("M")) {
                return parseInt / 1024;
            } else if (lastIndex.equals("K") || lastIndex.equals("KB")) {
                return parseInt / (1024 * 1024);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }


    public static void main(String[] args) {

        System.out.println(disposeUnit("45286628K"));
    }
}

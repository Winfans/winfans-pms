package cn.edu.lingnan.utils;

/**
 * @author 杨炜帆
 * @description 字符串工具类
 */
public class StringUtil {
    public static String removeLastChar(String str) {
        if (str != null) {
            return str.substring(0, str.length() - 1);
        }
        return null;
    }
}

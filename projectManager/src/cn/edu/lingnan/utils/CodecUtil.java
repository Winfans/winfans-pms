package cn.edu.lingnan.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author 杨炜帆
 * @description 密码工具类
 */
public class CodecUtil {

    /**
     * 密码加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String passwordSha256Encode(String password) throws NoSuchAlgorithmException {

        // 采用SHA256加密
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        byte[] shaBytes = messageDigest.digest(password.getBytes());
        return parseByteToHexStr(shaBytes);
    }

    /**
     * 判断输入的密码与加密后的密码是否一致
     *
     * @param password 输入的密码
     * @param encodePassword 加密后的密码
     * @return
     */
    public static Boolean passwordConfirm(String password, String encodePassword) throws NoSuchAlgorithmException {
        return passwordSha256Encode(password).matches(encodePassword);
    }

    /**
     * 二进制转换成16进制，加密后的字节数组不能直接转换为字符串
     */
    private static String parseByteToHexStr(byte[] shaBytes) {
        StringBuilder stringBuffer = new StringBuilder();
        for (byte shaByte : shaBytes) {
            String hex = Integer.toHexString(shaByte & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            stringBuffer.append(hex.toUpperCase());
        }
        return stringBuffer.toString();
    }
}

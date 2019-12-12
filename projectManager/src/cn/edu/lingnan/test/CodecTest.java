package cn.edu.lingnan.test;

import cn.edu.lingnan.utils.CodecUtil;

import java.security.NoSuchAlgorithmException;

/**
 * @author 杨炜帆
 * @description 加密测试
 */
public class CodecTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // 密码加密
        String encodePassword = CodecUtil.passwordSha256Encode("12345678");

        System.out.println("加密后的密码：" + encodePassword);
        System.out.println("密码是否相等：" + CodecUtil.passwordConfirm("123456", encodePassword));
        System.out.println("密码是否相等：" + CodecUtil.passwordConfirm("8888", encodePassword));
    }
}

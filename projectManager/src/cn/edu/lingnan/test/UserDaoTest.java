package cn.edu.lingnan.test;


import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.enums.RoleEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.UUID;


/**
 * @author 杨炜帆
 * @description 用户DAO测试
 */
public class UserDaoTest {

    private static UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {

        // 测试UUID生成
        System.out.println(UUID.randomUUID());


        // 测试插入一条记录，相当于注册一个用户信息
//        testInsertUser();

        // 测试按用户名和密码查找用户是否存在，如果存在返回真，否则返回假
        // testFindUserByUsernameAndPassword();

        // 测试更新用户信息
        // testUpdateUser();

        // 测试查找所有用户信息
        // testFindAllUser();

        // 测试根据用户id删除一条用户记录
        // testDeleteUserByUserId();

        // 测试根据用户id查找用户
        // testFindUserByUserId();

        // 测试修改密码
        // testUpdatePasswordByUserId();

        // 测试分页查找所有用户信息
//        testFindAllUserByPageAndLimit();


    }

    /**
     * 测试插入一条记录，相当于注册一个用户信息
     */
    public static void testInsertUser() {
        System.out.println("测试插入一条记录，相当于注册一个用户信息");
        for (int i = 1; i < 100; i++) {
            User user = new User();
            user.setUsername("用户" + i);
            user.setPassword("12345678");
            user.setHeadPortrait("http://t.cn/RCzsdCq");
            System.out.println("插入用户成功\n");

            userDAO.insertUser(user);
        }


    }

    /**
     * 测试按用户名和密码查找用户是否存在，如果存在返回真，否则返回假
     */
    private static void testLogin() {
        System.out.println("测试按用户名和密码查找用户是否存在，如果存在返回真，否则返回假");
        System.out.println("用户是否存在：" + userDAO.login("userTest2", "userTest1"));
        System.out.println("用户是否存在：" + userDAO.login("userTest2", "12345"));
        System.out.println();
    }

    /**
     * 测试更新用户信息
     */
    private static void testUpdateUser() {
        System.out.println("测试更新用户信息");
        User user = new User();
        user.setUserId(3);
        user.setUsername("userTest");
        user.setHeadPortrait("http://localhost:8080/image/11.jpg");
        user.setIsAdmin(RoleEnum.USER.getRoleId());
        userDAO.updateUser(user);
        System.out.println("更新用户信息成功\n");
    }

    /**
     * 测试查找所有用户信息
     */
    public static void testFindAllUser() {
        System.out.println("测试查找所有用户信息");
        userDAO.findAllUser().forEach(System.out::println);
        System.out.println();

    }

    /**
     * 测试根据用户id删除一条用户记录
     */
    public static void testDeleteUserByUserId() {
        System.out.println("测试根据用户id删除一条用户记录");
        userDAO.deleteUserByUserId(5);
        System.out.println("删除用户记录成功\n");
    }

    /**
     * 测试根据用户id查找用户
     */
    public static void testFindUserByUserId() {
        System.out.println("测试根据用户id查找用户");
        System.out.println(userDAO.findUserByUserId(4));
        System.out.println();
    }

    /**
     * 测试修改密码
     */
    public static void testUpdatePasswordByUserId() {
        System.out.println("测试修改密码");
        userDAO.updatePasswordByUserId(4, "123456", "888888");
        System.out.println("修改成功");
    }

    /**
     * 测试分页查找所有用户信息
     */
    public static void testFindAllUserByPageAndLimit() {
        userDAO.findAllUserByPageAndLimit(1, 10).forEach(System.out::println);
    }
}

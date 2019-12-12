package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.enums.OperationStatusEnum;
import cn.edu.lingnan.enums.RoleEnum;
import cn.edu.lingnan.utils.CodecUtil;
import cn.edu.lingnan.utils.DataAccess;
import cn.edu.lingnan.utils.ObjectUtil;

import java.beans.Beans;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 用户DAO
 */
public class UserDAO {

    /**
     * @param username 用户名
     * @param password 密码
     * @return 返回用户身份
     * @description 按用户名和密码查找用户是否存在，返回对应对应身份
     */
    public RoleEnum login(String username, String password) {

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            stat = conn.createStatement();

            // sql语句，密码进行加密后执行查询
            String sql = "select * from user where username = '" + username + "' and password = '" + CodecUtil.passwordSha256Encode(password) + "'";

            // 执行查询得到结果
            rs = stat.executeQuery(sql);

            // 判断用户是否存在，返回用户身份
            if (rs.next()) {
                return rs.getInt("is_admin") == 1 ? RoleEnum.ADMIN : RoleEnum.USER;
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("==========密码加密失败==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, stat, rs);
        }
        return RoleEnum.ILLEGAL;
    }

    /**
     * @param username
     * @param password
     * @return
     * @description 按用户名和密码查找用户
     */
    public User findUserByUsernameAndPassword(String username, String password) {

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        User user = new User();

        try {
            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            stat = conn.createStatement();

            // sql语句，密码进行加密后执行查询
            String sql = "select * from user where username = '" + username + "' and password = '" + CodecUtil.passwordSha256Encode(password) + "'";

            // 执行查询得到结果
            rs = stat.executeQuery(sql);

            // 判断用户是否存在
            if (rs.next()) {
                setUser(rs, user);
            }

        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("==========密码加密失败==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, stat, rs);
        }
        return user;
    }


    /**
     * @return 用户列表
     * @description 查找所有用户信息
     */
    public List<User> findAllUser() {

        List<User> userList = new LinkedList<>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            stat = conn.createStatement();

            String sql = "select * from user";

            // 执行查询得到结果
            rs = stat.executeQuery(sql);

            while (rs.next()) {

                User user = new User();
                setUser(rs, user);

                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, stat, rs);
        }

        return userList;
    }

    /**
     * @param username
     * @return
     * @description 通过用户名查找所有用户
     */
    public List<User> findAllUserByUsername(String username) {

        List<User> userList = new LinkedList<>();

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {

            conn = DataAccess.getConnection();

            preparedStatement = conn.prepareStatement("select * from user where username = ?");

            preparedStatement.setString(1, username.trim());

            // 执行查询得到结果
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                User user = new User();

                setUser(rs, user);

                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, preparedStatement, rs);
        }

        return userList;
    }

    /**
     * @param page
     * @param limit
     * @return
     * @description 分页查找所有用户信息
     */
    public List<User> findAllUserByPageAndLimit(int page, int limit) {

        // mysql分页从0开始，故需要进行处理
        page = page > 1 ? page - 1 : 0;

        List<User> userList = new LinkedList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {

            conn = DataAccess.getConnection();
            String sql = "SELECT * FROM user limit " + (limit * page) + "," + limit;

            // 创建SQL语句对象
            preparedStatement = conn.prepareStatement(sql);

            // 执行查询得到结果
            rs = preparedStatement.executeQuery(sql);

            while (rs.next()) {

                User user = new User();
                setUser(rs, user);

                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, preparedStatement, rs);
        }

        return userList;
    }

    /**
     * @param user 用户
     * @return 操作状态枚举
     * @description 插入一条记录，相当于注册一个用户信息
     */
    public OperationStatusEnum insertUser(User user) {

        Connection conn = null;
        PreparedStatement prep = null;

        try {

            // 密码加密存储
            user.setPassword(CodecUtil.passwordSha256Encode(user.getPassword()));

            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            user.setUpdateTime(new Timestamp(System.currentTimeMillis()));

            // 设置用户的角色为普通用户
            user.setIsAdmin(RoleEnum.USER.getRoleId());

            ObjectUtil.setDefaultValueToObject(user);

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            String sql = "insert into user(username, password, head_portrait, create_time, update_time, is_admin, sex, phone, city, interest, personalized_signature) values(?, ?, ?,  ?, ?, ?,?, ?, ?, ?, ?)";
            prep = conn.prepareStatement(sql);

            prep.setString(1, user.getUsername());
            prep.setString(2, user.getPassword());
            prep.setString(3, user.getHeadPortrait());
            prep.setTimestamp(4, user.getCreateTime());
            prep.setTimestamp(5, user.getUpdateTime());
            prep.setInt(6, user.getIsAdmin());
            prep.setInt(7, user.getSex());
            prep.setString(8, user.getPhone());
            prep.setString(9, user.getCity());
            prep.setString(10, user.getInterest());
            prep.setString(11, user.getPersonalizedSignature().trim());

            prep.executeUpdate();

        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("==========密码加密失败==========");
            e.printStackTrace();
            return OperationStatusEnum.OPERATION_FAIL;
        } finally {
            DataAccess.closeConnection(conn, prep);
        }
        return OperationStatusEnum.OPERATION_SUCCESS;
    }

    /**
     * @param userId
     * @return
     * @description 根据用户id删除一条用户记录
     */
    public OperationStatusEnum deleteUserByUserId(Integer userId) {

        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        PreparedStatement prep3 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            conn = DataAccess.getConnection();

            // 删除项目-用户关联表的某些记录,需要project_id,根据userId取出项目-用户关联表的所有数据
            prep1 = conn.prepareStatement
                    ("select * from project_user where user_id = ?");
            prep1.setInt(1, userId);
            rs1 = prep1.executeQuery();

            HashSet<Integer> hashSet = new HashSet<>();
            while (rs1.next()) {
                // 判断这个项目能不能删除，如果这个项目只有一个人，这个项目要删除
                prep2 = conn.prepareStatement
                        ("select count(*) as num from project_user where project_id = ?");
                prep2.setInt(1, rs1.getInt("project_id"));
                rs2 = prep2.executeQuery();

                while (rs2.next()) {
                    if (Integer.parseInt(rs2.getString("num")) == 1) {
                        hashSet.add(rs1.getInt("project_id"));
                    }
                }
            }

            conn.setAutoCommit(false);

            // 先删项目-用户表
            prep3 = conn.prepareStatement
                    ("delete from project_user where user_id = ?");
            prep3.setInt(1, userId);
            prep3.executeUpdate();

            // 再删用户表
            prep3 = conn.prepareStatement
                    ("delete from user where user_id = ?");
            prep3.setInt(1, userId);
            prep3.executeUpdate();

            // 最后删除项目表
            Iterator<Integer> it = hashSet.iterator();
            while (it.hasNext()) {
                prep3 = conn.prepareStatement
                        ("delete from project where project_id = ?");
                prep3.setInt(1, it.next());
                prep3.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {

            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                return OperationStatusEnum.OPERATION_FAIL;
            }
            e.printStackTrace();
            return OperationStatusEnum.OPERATION_FAIL;
        } finally {

            // 关闭数据库连接
            DataAccess.closeConnection(conn, new PreparedStatement[]{prep1, prep2, prep3}, new ResultSet[]{rs1, rs2});
        }
        return OperationStatusEnum.OPERATION_SUCCESS;
    }

    /**
     * @param user 用户
     * @return 操作状态枚举
     * @description 更新用户信息，参数中中包含用户id
     */
    public OperationStatusEnum updateUser(User user) {

        Connection conn = null;
        PreparedStatement prep = null;

        try {

            user.setUpdateTime(new Timestamp(System.currentTimeMillis()));

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            prep = conn.prepareStatement
                    ("update user set username = ?, head_portrait = ?, update_time = ?, is_admin = ?, sex = ?, phone = ?, city = ?, interest = ?, personalized_signature = ? where user_id = ?");

            prep.setString(1, user.getUsername());
            prep.setString(2, user.getHeadPortrait());
            prep.setTimestamp(3, user.getUpdateTime());
            prep.setInt(4, user.getIsAdmin());
            prep.setInt(5, user.getSex());
            prep.setString(6, user.getPhone());
            prep.setString(7, user.getCity());
            prep.setString(8, user.getInterest());
            prep.setString(9, user.getPersonalizedSignature().trim());
            prep.setInt(10, user.getUserId());

            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
            return OperationStatusEnum.OPERATION_FAIL;
        } finally {
            DataAccess.closeConnection(conn, prep);
        }
        return OperationStatusEnum.OPERATION_SUCCESS;

    }

    /**
     * @param userId 用户id
     * @return 用户
     * @description 根据用户id查找用户
     */
    public User findUserByUserId(Integer userId) {

        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;

        User user = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            String sql = "select * from user where user_id = ?";
            prep = conn.prepareStatement(sql);

            prep.setInt(1, userId);

            // 执行查询得到结果
            rs = prep.executeQuery();

            // 移动游标指向下一条
            rs.next();

            user = new User();
            setUser(rs, user);

        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }

        return user;
    }

    /**
     * @param userId      用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 操作状态枚举
     * @description 修改密码
     */
    public OperationStatusEnum updatePasswordByUserId(Integer userId, String oldPassword, String newPassword) {

        Connection conn = null;
        PreparedStatement prep = null;

        try {

            User user = findUserByUserId(userId);
            user.setUpdateTime(new Timestamp(System.currentTimeMillis()));

            if (!CodecUtil.passwordConfirm(oldPassword, user.getPassword())) {
                return OperationStatusEnum.PASSWORD_ERROR;
            }

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            prep = conn.prepareStatement
                    ("update user set password = ? where user_id = ?");

            // 新密码加密存储
            prep.setString(1, CodecUtil.passwordSha256Encode(newPassword));

            prep.setInt(2, userId);

            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
            return OperationStatusEnum.OPERATION_FAIL;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("==========密码加密失败==========");
            e.printStackTrace();
            return OperationStatusEnum.OPERATION_FAIL;
        } finally {
            DataAccess.closeConnection(conn, prep);
        }
        return OperationStatusEnum.OPERATION_SUCCESS;

    }

    /**
     * @param username
     * @description 通过用户名查找用户
     */
    public User findUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;

        User user = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            String sql = "select * from user where username = ?";
            prep = conn.prepareStatement(sql);

            prep.setString(1, username);

            // 执行查询得到结果
            rs = prep.executeQuery();

            // 移动游标指向下一条
            if (rs.next()) {
                user = new User();
                setUser(rs, user);
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }

        return user;
    }


    /**
     * @param rs
     * @param user
     * @throws SQLException
     * @description 将数据库取出来的结果集按照对应字段copy到user中, 包含user所有字段
     */
    private void setUser(ResultSet rs, User user) throws SQLException {
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setHeadPortrait(rs.getString("head_portrait"));
        user.setCreateTime(rs.getTimestamp("create_time"));
        user.setUpdateTime(rs.getTimestamp("update_time"));
        user.setIsAdmin(rs.getInt("is_admin"));
        user.setSex(rs.getInt("sex"));
        user.setPhone(rs.getString("phone"));
        user.setCity(rs.getString("city"));
        user.setInterest(rs.getString("interest"));
        user.setPersonalizedSignature(rs.getString("personalized_signature").trim());
    }


}


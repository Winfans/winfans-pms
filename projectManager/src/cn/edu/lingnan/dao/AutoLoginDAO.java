package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.AutoLogin;
import cn.edu.lingnan.enums.OperationStatusEnum;
import cn.edu.lingnan.utils.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 杨炜帆
 * @description 自动登录DAO
 */
public class AutoLoginDAO {

    /**
     * @param autoLogin
     * @return
     * @description 插入一条自动登录记录
     */
    public OperationStatusEnum insertAutoLogin(AutoLogin autoLogin) {

        Connection conn = null;
        PreparedStatement prep = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            prep = conn.prepareStatement
                    ("insert into auto_login(user_id, session_id) values(?, ?)");

            prep.setInt(1, autoLogin.getUserId());
            prep.setString(2, autoLogin.getSessionId());

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
     * @param userId
     * @param sessionId
     * @return
     * @description 断数据库中是否存在该用户id和session id
     */
    public boolean isExistsByUserIdAndSessionId(Integer userId, String sessionId) {

        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            String sql = "select * from auto_login where user_id = ? and session_id = ?";
            prep = conn.prepareStatement(sql);

            prep.setInt(1, userId);
            prep.setString(2, sessionId);

            // 执行查询得到结果
            rs = prep.executeQuery();

            // 移动游标指向下一条
            return rs.next();

        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }

        return false;
    }

    /**
     * @param userId
     * @param sessionId
     * @return
     * @description 根据用户id和session id删除记录
     */
    public OperationStatusEnum deleteByUserIdAndSessionId(Integer userId, String sessionId) {

        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();

            prep = conn.prepareStatement
                    ("delete from auto_login where user_id = ? and session_id = ?");
            prep.setInt(1, userId);
            prep.setString(2, sessionId);
            prep.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return OperationStatusEnum.OPERATION_FAIL;
        } finally {

            // 关闭数据库连接
            DataAccess.closeConnection(conn, prep);
        }
        return OperationStatusEnum.OPERATION_SUCCESS;
    }


}

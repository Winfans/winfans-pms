package cn.edu.lingnan.utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author 杨炜帆
 * @description 数据库工具类
 */
public class DataAccess {

    public static String driver = null;
    public static String url = null;
    public static String user = null;
    public static String password = null;
    public static String xsdPath = "database.conf.xsd";
    public static String xmlPath = "database.conf.xml";

    static {
        String basePath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        xsdPath = basePath + xsdPath;
        xmlPath = basePath + xmlPath;
    }
    /**
     * @return 数据库连接对象
     * @description 获取数据库连接
     */
    public static Connection getConnection() {

        Connection conn = null;

        if (XmlValidator.validate(xsdPath, xmlPath)) {
            HashMap<String, String> hashMap = XmlParser.parser(xmlPath);
            driver = hashMap.get("driver");
            url = hashMap.get("url");
            user = hashMap.get("user");
            password = hashMap.get("password");
        }

        try {

            // 注册驱动程序
            Class.forName(driver);

            // 获取数据库连接
            conn = DriverManager.getConnection
                    (url, user, password);

        } catch (ClassNotFoundException e) {

            System.out.println("==========数据库连接用的Jar包加载有问题==========");
            e.printStackTrace();
        } catch (SQLException e) {

            System.out.println("==========数据库连接参数（用户密码或数据库名）有问题==========");
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * @param conn Connection
     * @param stat Statement
     * @param rs   ResultSet
     * @description 关闭数据库连接
     */
    public static void closeConnection(Connection conn, Statement stat, ResultSet rs) {
        try {

            if (rs != null) {
                rs.close();
            }

            if (stat != null) {
                stat.close();
            }

            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {

            System.out.println("==========关闭连接时发生了异常==========");
            e.printStackTrace();
        }
    }

    /**
     * @param conn Connection
     * @param prep PreparedStatement
     * @param rs   ResultSet
     * @description 关闭数据库连接
     */
    public static void closeConnection(Connection conn, PreparedStatement prep, ResultSet rs) {
        try {

            if (rs != null) {
                rs.close();
            }

            if (prep != null) {
                prep.close();
            }

            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {

            System.out.println("==========关闭连接时发生了异常==========");
            e.printStackTrace();
        }
    }


    /**
     * @param conn Connection
     * @param preps PreparedStatement[]
     * @param resultSets   ResultSet[]
     * @description 关闭数据库连接
     */
    public static void closeConnection(Connection conn, PreparedStatement[] preps, ResultSet[] resultSets) {

        try {
            for (ResultSet rs : resultSets) {
                if (rs != null) {
                    rs.close();
                }
            }

            for (PreparedStatement prep : preps) {
                if (prep != null) {
                    prep.close();
                }
            }

            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            System.out.println("==========关闭连接时发生了异常==========");
            e.printStackTrace();
        }
    }

    /**
     * @param conn Connection
     * @param prep PreparedStatement
     * @description 关闭数据库连接
     */
    public static void closeConnection(Connection conn, PreparedStatement prep) {
        try {

            if (prep != null) {
                prep.close();
            }

            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            System.out.println("==========关闭连接时发生了异常==========");
            e.printStackTrace();
        }
    }
}

package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.Project;
import cn.edu.lingnan.dto.ProjectUser;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.enums.OperationStatusEnum;
import cn.edu.lingnan.utils.DataAccess;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 项目-用户DAO
 */
public class ProjectUserDAO {

    /**
     * @param projectUser 项目
     * @return 返回是否插入成功
     * @description 插入一条项目-用户记录，相当于用户创建一个项目或者用户成为该项目的负责人等
     */
    public OperationStatusEnum insertProjectUser(ProjectUser projectUser) {

        Connection conn = null;
        PreparedStatement prep = null;

        // 用户id
        Integer userId = projectUser.getUserId();

        // 项目id
        Integer projectId = projectUser.getProjectId();

        try {

            conn = DataAccess.getConnection();

            // 查找用户是否存在
            if (existsUserByUserId(userId) == OperationStatusEnum.USER_NOT_FOUND) {
                return OperationStatusEnum.USER_NOT_FOUND;
            }

            // 查找项目是否存在
            if (existsProjectByProjectId(projectId) == OperationStatusEnum.PROJECT_NOT_FOUND) {
                return OperationStatusEnum.PROJECT_NOT_FOUND;
            }

            // 创建SQL语句对象
            prep = conn.prepareStatement
                    ("insert into project_user(user_id, project_id, role) values(?, ?, ?)");

            prep.setInt(1, userId);
            prep.setInt(2, projectId);
            prep.setInt(3, projectUser.getRole());

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
     * @param projectId 项目id
     * @param userId    用户id
     * @return 是否删除成功
     * @description 根据项目id和用户id删除一条项目-用户记录，相当于解除用户和项目之间的关联
     */
    public OperationStatusEnum deleteProjectUserByProjectIdAndUserId(Integer projectId, Integer userId) {

        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();

            // 删除项目-用户表记录
            prep = conn.prepareStatement
                    ("delete from project_user where project_id = ? and user_id = ?");
            prep.setInt(1, projectId);
            prep.setInt(2, userId);
            prep.executeUpdate();

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
            DataAccess.closeConnection(conn, prep);
        }
        return OperationStatusEnum.OPERATION_SUCCESS;
    }

    /**
     * @param projectName
     * @return
     * @description 通过项目名字查找所有项目-用户
     */
    public List<ProjectUser> findAllProjectUserByProjectName(String projectName) {

        List<ProjectUser> projectUserList = new LinkedList<>();

        Connection conn = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {

            conn = DataAccess.getConnection();

            preparedStatement1 = conn.prepareStatement("select * from project where project_name = ?");
            preparedStatement1.setString(1, projectName);
            // 执行查询得到结果
            rs1 = preparedStatement1.executeQuery();

            while (rs1.next()) {
                preparedStatement2 = conn.prepareStatement("select * from project_user where project_id = ?");
                preparedStatement2.setInt(1, rs1.getInt("project_id"));
                rs2 = preparedStatement2.executeQuery();
                // 执行查询得到结果
                while (rs2.next()) {
                    // 关联表数据
                    int projectId = rs2.getInt("project_id");
                    int userId = rs2.getInt("user_id");
                    ProjectDAO projectDAO = new ProjectDAO();
                    Project project = projectDAO.findProjectByProjectId(projectId);
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.findUserByUserId(userId);

                    ProjectUser projectUser = new ProjectUser();

                    setProjectUser(rs2, projectUser);

                    projectUser.setProjectName(project.getProjectName());
                    projectUser.setUsername(user.getUsername());

                    projectUserList.add(projectUser);
                }
            }


        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, new PreparedStatement[]{preparedStatement1, preparedStatement2}, new ResultSet[]{rs1, rs2});
        }

        return projectUserList;
    }


    /**
     * @param projectId 项目id
     * @param userId    用户id
     * @return 项目
     * @description 根据项目id和用户id查找项目-用户记录
     */
    public ProjectUser findProjectUserByProjectIdAndUserId(Integer projectId, Integer userId) {

        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;

        ProjectUser projectUser = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            String sql = "select * from project_user where project_id = ? and user_id = ?";
            prep = conn.prepareStatement(sql);

            prep.setInt(1, projectId);
            prep.setInt(2, userId);

            // 执行查询得到结果
            rs = prep.executeQuery();

            // 移动游标指向下一条
            rs.next();

            projectUser = new ProjectUser();
            setProjectUser(rs, projectUser);

        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }

        return projectUser;
    }

    /**
     * @return 项目列表
     * @description 查找所有项目-用户
     */
    public List<ProjectUser> findAllProjectUser() {

        List<ProjectUser> projectUserList = new LinkedList<>();

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            stat = conn.createStatement();

            String sql = "select * from project_user";

            // 执行查询得到结果
            rs = stat.executeQuery(sql);

            while (rs.next()) {

                // 关联表数据
                int projectId = rs.getInt("project_id");
                int userId = rs.getInt("user_id");
                ProjectDAO projectDAO = new ProjectDAO();
                Project project = projectDAO.findProjectByProjectId(projectId);
                UserDAO userDAO = new UserDAO();
                User user = userDAO.findUserByUserId(userId);
                ProjectUser projectUser = new ProjectUser();

                setProjectUser(rs, projectUser);

                projectUser.setProjectName(project.getProjectName());
                projectUser.setUsername(user.getUsername());

                projectUserList.add(projectUser);
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, stat, rs);
        }

        return projectUserList;
    }


    /**
     * @param page
     * @param limit
     * @return
     * @description 分页查找所有项目-用户
     */
    public List<ProjectUser> findAllProjectUserByPageAndLimit(int page, int limit) {

        // mysql分页从0开始，故需要进行处理
        page = page > 1 ? page - 1 : 0;

        List<ProjectUser> projectUserList = new LinkedList<>();

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            stat = conn.createStatement();

            String sql = "select * from project_user limit " + (limit * page) + "," + limit;

            // 执行查询得到结果
            rs = stat.executeQuery(sql);

            while (rs.next()) {

                // 关联表数据
                int projectId = rs.getInt("project_id");
                int userId = rs.getInt("user_id");
                ProjectDAO projectDAO = new ProjectDAO();
                Project project = projectDAO.findProjectByProjectId(projectId);
                UserDAO userDAO = new UserDAO();
                User user = userDAO.findUserByUserId(userId);

                ProjectUser projectUser = new ProjectUser();

                setProjectUser(rs, projectUser);

                projectUser.setProjectName(project.getProjectName());
                projectUser.setUsername(user.getUsername());

                projectUserList.add(projectUser);
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, stat, rs);
        }

        return projectUserList;
    }

    /**
     * @param projectUser 项目
     * @return 返回操作状态枚举
     * @description 更新项目-用户信息，参数中包含项目id和用户id
     */
    public OperationStatusEnum updateProjectUser(ProjectUser projectUser) {

        Connection conn = null;
        PreparedStatement prep = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            prep = conn.prepareStatement
                    ("update project_user set role = ? where project_id = ? and user_id = ?");

            prep.setInt(1, projectUser.getRole());
            prep.setInt(2, projectUser.getProjectId());
            prep.setInt(3, projectUser.getUserId());

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
     * @return
     * @description 根据用户id查看用户是否存在
     */
    public OperationStatusEnum existsUserByUserId(Integer userId) {

        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;

        try {
            conn = DataAccess.getConnection();

            // 查找用户是否存在
            prep = conn.prepareStatement
                    ("select * from user where user_id = ?");
            prep.setInt(1, userId);
            rs = prep.executeQuery();

            // 不存在
            if (!rs.next()) {
                return OperationStatusEnum.USER_NOT_FOUND;
            }

        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
            return OperationStatusEnum.OPERATION_FAIL;

        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }
        return OperationStatusEnum.OPERATION_SUCCESS;

    }

    /**
     * @param projectId
     * @return
     * @description 根据项目id查看用户是否存在
     */
    public OperationStatusEnum existsProjectByProjectId(Integer projectId) {

        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;

        try {

            conn = DataAccess.getConnection();

            // 查找用户是否存在
            prep = conn.prepareStatement
                    ("select * from project where project_id = ?");
            prep.setInt(1, projectId);
            rs = prep.executeQuery();

            // 不存在
            if (!rs.next()) {
                return OperationStatusEnum.PROJECT_NOT_FOUND;
            }

        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
            return OperationStatusEnum.OPERATION_FAIL;
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }
        return OperationStatusEnum.OPERATION_SUCCESS;
    }


    /**
     * @param rs
     * @param projectUser
     * @throws SQLException
     * @description 将数据库取出来的结果集按照对应字段copy到project中, 包含project所有字段
     */
    private void setProjectUser(ResultSet rs, ProjectUser projectUser) throws SQLException {
        projectUser.setUserId(rs.getInt("user_id"));
        projectUser.setProjectId(rs.getInt("project_id"));
        projectUser.setRole(rs.getInt("role"));
    }
}


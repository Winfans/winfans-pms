package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.Project;
import cn.edu.lingnan.enums.OperationStatusEnum;
import cn.edu.lingnan.utils.DataAccess;
import cn.edu.lingnan.utils.ObjectUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 项目DAO
 */
public class ProjectDAO {

    /**
     * @param project 项目
     * @return 返回是否插入成功
     * @description 插入一条项目记录
     */
    public OperationStatusEnum insertProject(Project project) {

        Connection conn = null;
        PreparedStatement prep = null;

        try {

            project.setCreateTime(new Timestamp(System.currentTimeMillis()));

            ObjectUtil.setDefaultValueToObject(project);

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            prep = conn.prepareStatement
                    ("insert into project(project_name, content, creator, leader, plan_start_time, plan_end_time, plan_time, create_time, update_time, total_money, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            setPrep(project, prep);

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
     * @return 是否删除成功
     * @description 根据项目id删除一条项目记录
     */
    public OperationStatusEnum deleteProjectByProjectId(Integer projectId) {

        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();

            conn.setAutoCommit(false);

            // 先删项目-用户表记录
            prep = conn.prepareStatement
                    ("delete from project_user where project_id = ?");
            prep.setInt(1, projectId);
            prep.executeUpdate();

            // 最后删除项目表记录
            prep = conn.prepareStatement
                    ("delete from project where project_id = ?");
            prep.setInt(1, projectId);
            prep.executeUpdate();

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
            DataAccess.closeConnection(conn, prep);
        }
        return OperationStatusEnum.OPERATION_SUCCESS;
    }

    /**
     * @param projectId 项目id
     * @return 项目
     * @description 根据项目id查找项目
     */
    public Project findProjectByProjectId(Integer projectId) {

        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;

        Project project = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            String sql = "select * from project where project_id = ?";
            prep = conn.prepareStatement(sql);

            prep.setInt(1, projectId);

            // 执行查询得到结果
            rs = prep.executeQuery();

            // 移动游标指向下一条
            rs.next();

            project = new Project();
            setProject(rs, project);

        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }

        return project;
    }

    /**
     * @param projectName
     * @return
     * @description 通过项目名字查找所有项目
     */
    public List<Project> findAllProjectByProjectName(String projectName) {

        List<Project> projectList = new LinkedList<>();

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {

            conn = DataAccess.getConnection();

            preparedStatement = conn.prepareStatement("select * from project where project_name = ?");

            preparedStatement.setString(1, projectName);
            // 执行查询得到结果
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Project project = new Project();

                setProject(rs, project);

                projectList.add(project);
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, preparedStatement, rs);
        }

        return projectList;
    }


    /**
     * @return 项目列表
     * @description 查找所有项目
     */
    public List<Project> findAllProject() {

        List<Project> projectList = new LinkedList<>();

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            stat = conn.createStatement();

            String sql = "select * from project";

            // 执行查询得到结果
            rs = stat.executeQuery(sql);

            while (rs.next()) {

                Project project = new Project();

                setProject(rs, project);

                projectList.add(project);
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, stat, rs);
        }

        return projectList;
    }

    /**
     * @param page
     * @param limit
     * @return 项目列表
     * @description 分页查找所有项目
     */
    public List<Project> findAllProjectByPageAndLimit(int page, int limit) {

        // mysql分页从0开始，故需要进行处理
        page = page > 1 ? page - 1 : 0;

        List<Project> projectList = new LinkedList<>();

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            stat = conn.createStatement();

            String sql = "select * from project limit " + (limit * page) + "," + limit;

            // 执行查询得到结果
            rs = stat.executeQuery(sql);

            while (rs.next()) {

                Project project = new Project();

                setProject(rs, project);

                projectList.add(project);
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, stat, rs);
        }

        return projectList;
    }

    /**
     * @param project 项目
     * @return 返回是否更新成功
     * @description 更新项目信息，参数中包含项目id
     */
    public OperationStatusEnum updateProject(Project project) {
        Connection conn = null;
        PreparedStatement prep = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            prep = conn.prepareStatement
                    ("update project set project_name = ?, content = ?, creator = ?, leader = ?, plan_start_time = ?, plan_end_time = ?, plan_time = ?, create_time = ?, update_time = ?, total_money = ?, status = ? where project_id = ?");

            setPrep(project, prep);
            prep.setInt(12, project.getProjectId());

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
     * @param projectName
     * @return
     * @description 通过项目名获取项目
     */
    public Project findProjectByProjectName(String projectName) {
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;

        Project project = null;

        try {

            conn = DataAccess.getConnection();

            // 创建SQL语句对象
            String sql = "select * from project where project_name = ?";
            prep = conn.prepareStatement(sql);

            prep.setString(1, projectName);

            // 执行查询得到结果
            rs = prep.executeQuery();

            // 移动游标指向下一条
            if (rs.next()) {
                project = new Project();
                setProject(rs, project);
            }
        } catch (SQLException e) {
            System.out.println("==========sql语句有问题==========");
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }

        return project;
    }

    /**
     * @param project
     * @param prep
     * @throws SQLException
     * @description 封装预处理语句预处理参数的设置，不包含projectId
     */
    private void setPrep(Project project, PreparedStatement prep) throws SQLException {

        prep.setString(1, project.getProjectName());
        prep.setString(2, project.getContent());
        prep.setString(3, project.getCreator());
        prep.setString(4, project.getLeader());
        prep.setTimestamp(5, project.getPlanStartTime());
        prep.setTimestamp(6, project.getPlanEndTime());
        prep.setString(7, project.getPlanTime());
        prep.setTimestamp(8, project.getCreateTime());
        prep.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
        prep.setFloat(10, project.getTotalMoney());
        prep.setInt(11, project.getStatus());
    }

    /**
     * @param rs
     * @param project
     * @throws SQLException
     * @description 将数据库取出来的结果集按照对应字段copy到project中, 包含project所有字段
     */
    private void setProject(ResultSet rs, Project project) throws SQLException {
        project.setProjectId(rs.getInt("project_id"));
        project.setProjectName(rs.getString("project_name"));
        project.setContent(rs.getString("content"));
        project.setCreator(rs.getString("creator"));
        project.setLeader(rs.getString("leader"));
        project.setPlanStartTime(rs.getTimestamp("plan_start_time"));
        project.setPlanEndTime(rs.getTimestamp("plan_end_time"));
        project.setPlanTime(rs.getString("plan_time"));
        project.setCreateTime(rs.getTimestamp("create_time"));
        project.setUpdateTime(rs.getTimestamp("update_time"));
        project.setTotalMoney(rs.getInt("total_money"));
        project.setStatus(rs.getInt("status"));
    }


}


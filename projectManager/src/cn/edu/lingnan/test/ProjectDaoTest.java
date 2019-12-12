package cn.edu.lingnan.test;


import cn.edu.lingnan.dao.ProjectDAO;
import cn.edu.lingnan.dto.Project;
import cn.edu.lingnan.enums.ProjectStatusEnum;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 项目DAO测试
 */
public class ProjectDaoTest {

    private static ProjectDAO projectDAO = new ProjectDAO();

    public static void main(String[] args) {

        // 测试插入一条项目记录
        testInsertProject();

        // 测试根据项目id删除一条项目记录
//        testDeleteProjectByProjectId();

        // 测试根据项目id查找项目
//        testFindProjectByProjectId();

        // 测试查找所有项目
//        testFindAllProject();

        // 测试更新项目信息
//        testUpdateProject();

    }

    /**
     * @description 测试插入一条项目记录
     */
    private static void testInsertProject() {
        System.out.println("测试插入一条项目记录");
//        setProject(project);
        for (int i = 1; i < 100; i++) {
            Project project = new Project();
            project.setProjectName("项目" + i);
            project.setContent("项目内容" + i);
            project.setCreator("admin");
            project.setLeader("admin");
            project.setPlanStartTime(new Timestamp(System.currentTimeMillis()));
            project.setPlanEndTime(new Timestamp(System.currentTimeMillis()));
            project.setPlanTime("520天");
            project.setTotalMoney(520);
            // 设置项目状态为未开始，此处采用枚举对状态进行存储
            project.setStatus(ProjectStatusEnum.NOT_START.getStatusId());
            projectDAO.insertProject(project);
            System.out.println("插入成功\n");
        }

    }

    /**
     * @description 测试根据项目id删除一条项目记录
     */
    private static void testDeleteProjectByProjectId() {
        System.out.println("测试根据项目id删除一条项目记录");
        projectDAO.deleteProjectByProjectId(3);
        System.out.println("删除成功\n");
    }

    /**
     * @description 测试根据项目id查找项目
     */
    private static void testFindProjectByProjectId() {
        System.out.println("测试根据项目id查找项目");
        System.out.println(projectDAO.findProjectByProjectId(4));
        System.out.println();
    }

    /**
     * @description 测试查找所有项目
     */
    private static void testFindAllProject() {
        System.out.println("测试查找所有项目");
        List<Project> projectList = projectDAO.findAllProject();
        projectList.forEach(System.out::println);
        System.out.println();
    }

    /**
     * @description 测试更新项目信息
     */
    private static void testUpdateProject() {
        System.out.println("测试更新项目信息");
        Project project = new Project();
        project.setProjectId(4);
        setProject(project);
        // 设置项目状态为未开始，此处采用枚举对状态进行存储
        project.setStatus(ProjectStatusEnum.NOT_START.getStatusId());
        projectDAO.updateProject(project);
        System.out.println("更新成功\n");
    }

    /**
     * @param project
     * @description 设置项目，不包含项目id
     */
    private static void setProject(Project project) {
        project.setProjectName("projectTest");
        project.setContent("projectTest");
        project.setCreator("userTest");
        project.setLeader("userTest");
        project.setPlanStartTime(new Timestamp(System.currentTimeMillis()));
        project.setPlanEndTime(new Timestamp(System.currentTimeMillis()));
        project.setPlanTime("520天");
        project.setTotalMoney(520);
    }
}

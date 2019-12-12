package cn.edu.lingnan.test;


import cn.edu.lingnan.dao.ProjectUserDAO;
import cn.edu.lingnan.dto.ProjectUser;

import java.util.List;

/**
 * @author 杨炜帆
 * @description 项目DAO测试
 */
public class ProjectUserDaoTest {

    private static ProjectUserDAO projectUserDAO = new ProjectUserDAO();

    public static void main(String[] args) {

        // 测试插入一条项目-用户记录
        testInsertProjectUser();

        // 测试根据项目id和用户id删除一条项目-用户记录
//        testDeleteProjectUserByProjectIdAndUserId();

        // 测试根据项目id和用户id查找项目-用户记录
//        testFindProjectUserByProjectIdAndUserId();

        // 测试查找所有项目-用户
//        testFindAllProjectUser();

        // 测试更新项目-用户信息，参数中包含项目id和用户id
//        testUpdateProjectUser();

    }

    /**
     * @description 测试插入一条项目-用户记录
     */
    private static void testInsertProjectUser() {
        System.out.println("测试插入一条项目-用户记录");
        for (int i = 224; i < 250; i++) {
            ProjectUser projectUser = new ProjectUser();
            projectUser.setUserId(i);
            projectUser.setProjectId(13);
            projectUser.setRole(3);
            projectUserDAO.insertProjectUser(projectUser);
        }
        System.out.println("插入成功\n");
    }

    /**
     * @description 测试根据项目id和用户id删除一条项目-用户记录
     */
    private static void testDeleteProjectUserByProjectIdAndUserId() {
        System.out.println("测试根据项目id和用户id删除一条项目-用户记录");
        projectUserDAO.deleteProjectUserByProjectIdAndUserId(9, 3);
        System.out.println("删除成功\n");
    }

    /**
     * @description 测试根据项目id和用户id查找项目-用户记录
     */
    private static void testFindProjectUserByProjectIdAndUserId() {
        System.out.println("测试根据项目id和用户id查找项目-用户记录");
        System.out.println(projectUserDAO.findProjectUserByProjectIdAndUserId(3, 4));
        System.out.println();
    }

    /**
     * @description 测试查找所有项目-用户
     */
    private static void testFindAllProjectUser() {
        System.out.println("测试查找所有项目-用户");
        List<ProjectUser> projectUserList = projectUserDAO.findAllProjectUser();
        projectUserList.forEach(System.out::println);
        System.out.println();
    }

    /**
     * @description 测试更新项目-用户信息，参数中包含项目id和用户id
     */
    private static void testUpdateProjectUser() {
        System.out.println("测试更新项目-用户信息，参数中包含项目id和用户id");
        ProjectUser projectUser = new ProjectUser();
        setProjectUser(projectUser);
        projectUserDAO.updateProjectUser(projectUser);
        System.out.println("更新成功\n");
    }

    /**
     * @description 设置项目-用户，包含所有字段
     * @param projectUser
     */
    private static void setProjectUser(ProjectUser projectUser) {
        projectUser.setUserId(4);
        projectUser.setProjectId(4);
        projectUser.setRole(1);
    }
}

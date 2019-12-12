package cn.edu.lingnan.dto;

/**
 * @author 杨炜帆
 * @description 项目-用户
 */
public class ProjectUser {

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色
     * 1、项目管理员
     * 2、项目负责人
     * 3、项目成员
     */
    private Integer role;

    private String username;

    private String projectName;


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return projectId + "\t" + userId;
    }
}

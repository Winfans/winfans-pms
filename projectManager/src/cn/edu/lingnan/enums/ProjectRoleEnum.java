package cn.edu.lingnan.enums;

/**
 * @author 杨炜帆
 * @description 角色枚举
 */
public enum ProjectRoleEnum {

    /**
     * 项目管理员
     */
    PROJECT_ADMIN(1, "项目管理员"),

    /**
     * 项目负责人
     */
    PROJECT_LEADER(2, "项目负责人"),

    /**
     * 项目成员
     */
    PROJECT_MEMBER(3, "项目成员");

    private Integer roleId;
    private String roleName;

    ProjectRoleEnum(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}

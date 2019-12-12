package cn.edu.lingnan.enums;

/**
 * @author 杨炜帆
 * @description 角色枚举
 */
public enum RoleEnum {

    /**
     * 非法用户
     */
    ILLEGAL(0, "非法用户"),

    /**
     * 管理员
     */
    ADMIN(1, "管理员"),

    /**
     * 普通用户
     */
    USER(2, "普通用户");

    private Integer roleId;
    private String roleName;

    RoleEnum(Integer roleId, String roleName) {
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

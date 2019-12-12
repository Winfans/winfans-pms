package cn.edu.lingnan.enums;


/**
 * @author 杨炜帆
 * @description 操作状态枚举
 */
public enum OperationStatusEnum {
    /**
     * 操作成功
     */
    OPERATION_SUCCESS,

    /**
     * 操作失败
     */
    OPERATION_FAIL,

    /**
     * 没有权限
     */
    PERMISSION_DENIED,

    /**
     * 用户找不到
     */
    USER_NOT_FOUND,

    /**
     * 项目找不到
     */
    PROJECT_NOT_FOUND,

    /**
     * 密码错误
     */
    PASSWORD_ERROR;


}

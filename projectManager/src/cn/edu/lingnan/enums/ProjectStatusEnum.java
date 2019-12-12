package cn.edu.lingnan.enums;

/**
 * @author 杨炜帆
 * @description 项目状态枚举，用来描述项目的状态
 */
public enum ProjectStatusEnum {

    /**
     * 未开始
     */
    NOT_START(1, "未开始"),

    /**
     * 进行中
     */
    IN_PROGRESS(2, "进行中"),

    /**
     * 已完成
     */
    COMPLETED(3, "已完成");

    private Integer statusId;
    private String statusName;

    ProjectStatusEnum(Integer statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }
}

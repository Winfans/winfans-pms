package cn.edu.lingnan.dto;

import java.sql.Timestamp;

/**
 * @author 杨炜帆
 * @description 项目
 */
public class Project {

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 计划开始时间
     */
    private Timestamp planStartTime;

    /**
     * 计划结束时间
     */
    private Timestamp planEndTime;

    /**
     * 项目计划周期
     */
    private String planTime;

    /**
     * 总金额
     */
    private Integer totalMoney;

    /**
     * 状态
     * 1、未开始
     * 2、进行中
     * 3、已完成
     */
    private Integer status;



    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Timestamp planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Timestamp getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Timestamp planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return projectId + "\t" + projectName;
    }
}

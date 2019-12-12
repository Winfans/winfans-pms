package cn.edu.lingnan.dto;

/**
 * @author 杨炜帆
 * @description 自动登录
 */
public class AutoLogin {

    private Integer userId;
    private String sessionId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

package cn.edu.lingnan.vo;

import cn.edu.lingnan.dto.User;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 杨炜帆
 * @description 用于操纵JSON数组
 */
public class JSONArray {

    public static void main(String[] args) {
        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("test1");
        user1.setIsAdmin(1);

        User user2 = new User();
        user2.setUserId(1);
        user2.setUsername("test2");
        user2.setIsAdmin(1);

        List<User> userList = new LinkedList<>();
        userList.add(user1);
        userList.add(user2);
        System.out.println(JSONArray.toJSONString(userList));

    }

    /**
     * @param list
     * @return
     * @description 将列表转化为json
     */
    public static String toJSONString(List<?> list) {

        StringBuilder stringBuilder = new StringBuilder("{\"status\": 0,\"message\":\"success\",\"total\":");
        stringBuilder.append(list.size()).append(",\"data\":[");
        list.forEach(consumer -> {
            stringBuilder.append(JSONObject.toJSONString(consumer)).append(",");
        });
        int stringBuilderLength = stringBuilder.length();
        return stringBuilder.replace(stringBuilderLength - 1, stringBuilderLength, "]}").toString();
    }

    /**
     * @param list
     * @param total
     * @return
     * @description 将列表转化为json
     */
    public static String toJSONString(List<?> list, int total) {

        StringBuilder stringBuilder = new StringBuilder("{\"status\": 0,\"message\":\"success\",\"total\":");
        stringBuilder.append(total).append(",\"data\":[");
        list.forEach(consumer -> {
            stringBuilder.append(JSONObject.toJSONString(consumer)).append(",");
        });
        int stringBuilderLength = stringBuilder.length();
        return stringBuilder.replace(stringBuilderLength - 1, stringBuilderLength, "]}").toString();
    }

}

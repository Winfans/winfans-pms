package cn.edu.lingnan.vo;

import cn.edu.lingnan.dto.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 杨炜帆
 * @description 用于操纵JSON对象
 */
public class JSONObject {

    public static void main(String[] args) {
        User user = new User();
        user.setUserId(1);
        user.setUsername("test1");
        user.setIsAdmin(1);
        System.out.println(JSONObject.toJSONString(user));
    }

    /**
     * @param object
     * @return
     * @description 将对象转化为json
     */
    public static String toJSONString(Object object) {

        StringBuilder stringBuilder = new StringBuilder("{");

        Field[] declaredFields = object.getClass().getDeclaredFields();

        try {
            for (Field declaredField : declaredFields) {
                String fieldName = declaredField.getName();
                Method method = object.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                Object o2 = method.invoke(object);
                if (o2 == null) {
                    stringBuilder.append("\"").append(fieldName).append("\":").append((Object) null).append(",");
                } else if (o2 instanceof Number) {
                    stringBuilder.append("\"").append(fieldName).append("\":").append(o2).append(",");
                } else {
                    stringBuilder.append("\"").append(fieldName).append("\":\"").append(o2.toString()).append("\",");
                }
            }
            return stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "}").toString();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return "";
    }

}

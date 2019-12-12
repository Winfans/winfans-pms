package cn.edu.lingnan.utils;

import cn.edu.lingnan.dto.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author 杨炜帆
 * @description 对象工具类
 */
public class ObjectUtil {

    /**
     * @param object
     * @description 为对象中值为空的属性赋默认值
     */
    public static void setDefaultValueToObject(Object object) {
        // 通过反射获取User
        Class<?> objectClass = object.getClass();
        // 获取User的所有属性
        Field[] declaredFields = objectClass.getDeclaredFields();

        try {
            // 遍历User的所有属性
            for (Field declaredField : declaredFields) {

                // 获取属性的具体类型
                Type type = declaredField.getGenericType();

                // 获取属性的具体类型的名称
                String typeName = type.getTypeName();

                // 获取属性名称
                String fieldName = declaredField.getName();

                // 获取getter方法
                Method getterMethod = objectClass.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));

                // 如果getter获取到属性值为空
                if (getterMethod.invoke(object) == null) {

                    Method setterMethod;
                    String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                    // 如果属性类型为Integer，调用Integer的setter方法
                    if ("java.lang.Integer".equals(typeName)) {
                        setterMethod = objectClass.getDeclaredMethod(setterMethodName, Integer.class);
                        setterMethod.invoke(object, 0);

                        // 如果属性类型为String，调用String的setter方法
                    } else if ("java.lang.String".equals(typeName)) {
                        setterMethod = objectClass.getDeclaredMethod(setterMethodName, String.class);
                        setterMethod.invoke(object, "");
                    } else if ("java.lang.Float".equals(typeName)) {
                        setterMethod = objectClass.getDeclaredMethod(setterMethodName, Float.class);
                        setterMethod.invoke(object, 0f);
                    }
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

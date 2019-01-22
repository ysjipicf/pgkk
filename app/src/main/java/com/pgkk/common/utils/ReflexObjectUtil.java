package com.pgkk.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by tanxueze on 2018/1/4.
 */

public class ReflexObjectUtil<T> {

    public ReflexObjectUtil() {

    }

    /**
     * 单个对象的所有键值
     * <p>
     * 单个对象
     *
     * @return
     */
    public  Map<String, Object> getKeyAndValue(T obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(obj);
                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        System.out.println(map.toString());
        return map;
    }

    /**
     * 单个对象的某个键的值
     * <p>
     * 对象
     *
     * @param key 键
     * @return Object 键在对象中所对应得值 没有查到时返回空字符串
     */
    public  Object getValueByKey(T obj, String key) {
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            try {

                if (f.getName().endsWith(key)) {
                    System.out.println(f.get(obj));
                    return f.get(obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // 没有查到时返回空字符串
        return "";
    }

    /**
     * 多个（列表）对象的所有键值
     *
     * @param object
     * @return List<Map<String,Object>>
     */
    public List<Map<String, Object>> getKeysAndValues(List<T> object) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (T obj : object) {
            Class userCla;
            // 得到类对象
            userCla = (Class) obj.getClass();
            /* 得到类中的所有属性集合 */
            Field[] fs = userCla.getDeclaredFields();
            Map<String, Object> listChild = new HashMap<String, Object>();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true); // 设置些属性是可以访问的
                Object val = new Object();
                try {
                    val = f.get(obj);
                    // 得到此属性的值
                    listChild.put(f.getName(), val);// 设置键值
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.add(listChild);// 将map加入到list集合中
        }
        System.out.println( list.toString());
        return list;
    }

    /**
     * 多个（列表）对象的某个键的值
     *
     * @param object
     * @param key
     * @return List<Object> 键在列表中对应的所有值 ex:key
     */
    public List<Object> getValuesByKey(List<T> object, String key) {
        List<Object> list = new LinkedList<>();
        for (T obj : object) {
            // 得到类对象
            Class userCla = (Class) obj.getClass();
            /* 得到类中的所有属性集合 */
            Field[] fs = userCla.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true); // 设置些属性是可以访问的
                try {
                    if (f.getName().endsWith(key)) {
                        list.add(f.get(obj));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(list.toString());
        return list;
    }
}


package com.grain.utils.pageutils;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;


/**
 * 泛型工具类
 *
 * @author
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    /**
     * 提取集合中的对象的属性(通过Getter函数), 组合成Map.
     *
     * @param collection        来源集合.
     * @param keyPropertyName   要提取为Map中的Key值的属性名.
     * @param valuePropertyName 要提取为Map中的Value值的属性名.
     */
    public static Map extractToMap(Collection collection, String keyPropertyName, String valuePropertyName) {
        Map map = new HashMap();

        try {
            for (Object obj : collection) {
                map.put(PropertyUtils.getProperty(obj, keyPropertyName), PropertyUtils.getProperty(obj, valuePropertyName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 提取集合中的对象的属性(通过Getter函数), 组合成List.
     *
     * @param collection   来源集合.
     * @param propertyName 要提取的属性名.
     * @return List
     */
    public static <T> List<T> extractToList(Collection collection, String propertyName) {

        return extractToList(collection, propertyName, false);
    }

    /**
     * 提取集合中的对象的属性(通过Getter函数), 组合成List.
     *
     * @param collection       来源集合.
     * @param propertyName     要提取的属性名.
     * @param ignoreEmptyValue 是否过滤null值和""值
     * @return List
     */
    public static <T> List<T> extractToList(Collection collection, String propertyName, boolean ignoreEmptyValue) {
        if (collection == null) {
            return null;
        }
        List list = new ArrayList();

        try {
            for (Object obj : collection) {
                T value = (T) PropertyUtils.getProperty(obj, propertyName);
                if (ignoreEmptyValue && value == null || value.toString().equals("")) {
                    continue;
                }
                list.add(PropertyUtils.getProperty(obj, propertyName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 提取集合中的对象的属性(通过Getter函数), 组合成由分割符分隔的字符串.
     *
     * @param collection   来源集合.
     * @param propertyName 要提取的属性名.
     * @param separator    分隔符.
     */
    public static String extractToString(Collection collection, String propertyName, String separator) {
        List list = extractToList(collection, propertyName);
        return StringUtils.join(list, separator);
    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> transBean2Map(Object obj) {

        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性  
                if (!key.equals("class")) {
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            //System.out.println("transBean2Map Error " + e);
        }

        return map;

    }
}

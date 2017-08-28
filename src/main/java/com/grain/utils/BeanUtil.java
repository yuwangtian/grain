package com.grain.utils;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author anqi
 * @since 2014/8/14.
 */
public class BeanUtil {
    static String EMPTY_STRING = "";
    static boolean IGNORE_NULL_VALUE = true;

    public static String serialize(Object javabean) {
        ParserConfig parserConfig = new ParserConfig();
        StringBuilder sb = new StringBuilder();
        List<FieldInfo> getters = TypeUtils.computeGetters(javabean.getClass(), null);
        for (FieldInfo field : getters) {
            try {
                Object value = field.get(javabean);
                if (IGNORE_NULL_VALUE && value == null) {
                    continue;
                }
                if (value == null) {
                    value = EMPTY_STRING;
                }
                if (parserConfig.isPrimitive(field.getFieldClass())) {
                    sb.append("&").append(field.getName()).append("=").append(value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}

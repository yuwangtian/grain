package com.grain.utils.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class ReflectUtil {

    private static final Log logger = LogFactory.getLog(ReflectUtil.class);

    public static void setFieldValue(Object target, String fname, Class ftype,
                                     Object fvalue) {
        if (target == null
                || fname == null
                || "".equals(fname)
                || (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
            return;
        }
        Class clazz = target.getClass();
        try {
            String themethod = "set" + Character.toUpperCase(fname.charAt(0)) + fname.substring(1);
            Method method = clazz.getDeclaredMethod(themethod, new Class[]{ftype});
            if (!Modifier.isPublic(method.getModifiers())) {
                method.setAccessible(true);
            }
            method.invoke(target, new Object[]{fvalue});

        } catch (Exception me) {
            if (logger.isDebugEnabled()) {
                logger.debug(me);
            }
            try {
                Field field = clazz.getDeclaredField(fname);
                if (!Modifier.isPublic(field.getModifiers())) {
                    field.setAccessible(true);
                }
                field.set(target, fvalue);
            } catch (Exception fe) {
                if (logger.isDebugEnabled()) {
                    logger.debug(fe);
                }
            }
        }
    }

    public static Object getFieldValue(String fieldName, Object obj) {
        Method method = null;
        Object returnObj = null;
        String methodStr = "get";
        if (fieldName != null && fieldName.length() > 0) {
            String a = fieldName.substring(0, 1);
            methodStr += a.toUpperCase();
            methodStr += fieldName.substring(1);
        }
        try {
            method = obj.getClass().getMethod(methodStr);
            returnObj = method.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return returnObj;
    }

    public static HashMap bean2map(Object bean) {
        HashMap map = new HashMap();
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
        }
        if (props != null) {
            for (int i = 0; i < props.length; i++) {
                try {
                    String name = props[i].getName().toString();
                    Object value = props[i].getReadMethod().invoke(bean);
                    map.put(name, value);

                } catch (Exception e) {
                }
            }
        }
        return map;
    }
}
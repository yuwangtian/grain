package com.grain.utils.pageutils;


import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;


public class DtoJsonutil {

    /**
     * @param @param  obj
     * @param @throws Exception    璁惧畾鏂囦欢
     * @return void    杩斿洖绫诲瀷
     * @throws
     * @Title: doSpecialSign
     * @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鏂规硶鐨勪綔鐢�
     */
    public static void doSpecialSign(Object obj) throws Exception {
        Field field[] = obj.getClass().getDeclaredFields();
        for (Field target : field) {
            if (target.getType() != String.class) continue;
            String value = BeanUtils.getProperty(obj, target.getName());
            if (value != null) value = value.replace("\r\n", "<br>");
            BeanUtils.setProperty(obj, target.getName(), StringUtil.dealString4JSON(value));
        }
    }

    public static String doListJson(List list, int totalCount) throws Exception {
        StringBuffer str = new StringBuffer();
        //str.append("{'root':");
        str.append("{\"rows\":");
        if (list != null && list.size() > 0) {
            str.append("[");
            int i = 0;
            for (Object obj : list) {
                i++;
                doSpecialSign(obj);
                str.append("{");
                str.append(getJsonstrFromdto(obj));
                str.append("}");
                if (i < list.size()) str.append(",");
            }
            str.append("],\"total\":\"" + totalCount + "\"}");
        } else {
            str.append("[],\"total\":\"0\"}");
        }

        return str.toString();
    }

    /**
     * @param obj
     * @return
     * @throws Exception
     */
    public static String getJsonstrFromdto(Object obj) throws Exception {
        StringBuffer sb = new StringBuffer();

        Field fields[] = obj.getClass().getDeclaredFields();
        for (Field target : fields) {
            if (target.getType().getSuperclass() == DtoSupport.class) {
                String fieldName = target.getName();
                String firstLetter = fieldName.substring(0, 1).toUpperCase();

                String getMethodName = "get" + firstLetter + fieldName.substring(1);
                Method method = obj.getClass().getMethod(getMethodName, null);
                Object propertyobj = method.invoke(obj, null);
                if (propertyobj != null)
                    sb.append(",\"" + target.getName() + "\":{\"" + getJsonstrFromdto(propertyobj) + "\"}");
            } else {
                String value = BeanUtils.getProperty(obj, target.getName());
                if (value == null || "null".equals(value)) value = "";
                sb.append(",\"" + target.getName() + "\":\"" + StringUtil.dealString4JSON(value) + "\"");
            }
        }
        return sb.toString().substring(1);

    }

}

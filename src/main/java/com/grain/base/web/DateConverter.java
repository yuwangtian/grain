package com.grain.base.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2014/6/13.
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source))
            return null;
        SimpleDateFormat dateFormat = null;
        if (source.contains(":") && source.length() > 13) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateFormat.setLenient(false);
        } else if (source.length() > 9) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM");
            dateFormat.setLenient(false);
        }
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            try {
                return dateFormat.parse(source);
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
        }
        return null;
    }
}
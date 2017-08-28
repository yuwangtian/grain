package com.grain.base.web;

import org.springframework.core.convert.converter.Converter;

/**
 * @author anqi
 * @since 2014/7/16.
 */
public class StringConverter implements Converter<String, String> {
    @Override
    public String convert(String source) {
        return source.trim();
    }
}

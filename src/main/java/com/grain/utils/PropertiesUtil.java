package com.grain.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties loadProperties(String configpath) {
        Properties properties = new Properties();
        try {
            InputStream localInputStream = PropertiesUtil.class.getResourceAsStream(configpath);
            properties.load(localInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}

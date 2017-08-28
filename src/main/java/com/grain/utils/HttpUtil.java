package com.grain.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/6/11.
 */
public class HttpUtil {
    private static Logger logger = Logger.getLogger(HttpUtil.class);

    /**
     * 获取url中name参数的値
     *
     * @param url
     * @param name
     * @return
     */
    public static String getParameter(String url, String name) {
        String value = null;
        try {
            URI uri = new URI(url);
            String query = uri.getQuery();
            if (query != null) {
                String[] qs = query.split("&");
                for (String q : qs) {
                    String[] s = q.split("=");
                    if (s[0].equalsIgnoreCase(name)) {
                        value = s[1];
                    }
                }
            }
        } catch (URISyntaxException e) {
            logger.debug(e.getMessage());
        }
        return value;
    }

    public static void crawl(String url) {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            try {
                String resString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                //System.out.println(resString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将url中的参数放到map中
     *
     * @param urlStr
     * @return
     */
    public static Map<String, String> urlParser(String urlStr) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            URI url = null;
            url = new URI(urlStr);
            String query = url.getQuery();
            if (query != null) {
                String[] qs = query.split("&");
                for (String q : qs) {
                    String[] s = q.split("=");
                    map.put(s[0], s[1]);
                }
            }
        } catch (URISyntaxException e) {
            logger.debug(e.getMessage());
        }
        return map;
    }

    /**
     * 添加或修改url中name参数的值
     *
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String setParameter(String url, String name, String value) {
        try {
            URI uri = new URI(url);
            String path = uri.getScheme() + "://" + uri.getHost() + uri.getPath();
            String query = uri.getQuery();
            StringBuffer qbuffer = null;
            if (query != null) {
                String[] qs = query.split("&");
                boolean hasParam = false;
                for (String q : qs) {
                    String[] s = q.split("=");
                    if (name.equals(s[0])) {
                        hasParam = true;
                        q = name + "=" + value;
                    }
                    if (qbuffer == null) {
                        qbuffer = new StringBuffer();
                        qbuffer.append("?").append(q);
                    } else {
                        qbuffer.append("&").append(q);
                    }
                }
                if (!hasParam) {
                    String q = name + "=" + value;
                    if (qbuffer == null) {
                        qbuffer = new StringBuffer();
                        qbuffer.append("?").append(q);
                    } else {
                        qbuffer.append("&").append(q);
                    }
                }
            } else {
                qbuffer = new StringBuffer();
                qbuffer.append("?").append(name).append("=").append(value);
            }
            url = path + qbuffer.toString();
        } catch (URISyntaxException e) {
            logger.debug(e.getMessage());
        }
        return url;
    }

    /**
     * 删除url中的name参数
     *
     * @param url
     * @param name
     * @return
     */
    public static String removeParameter(String url, String name) {
        try {
            URI uri = new URI(url);
            String path = uri.getScheme() + "://" + uri.getHost() + uri.getPath();
            String query = uri.getQuery();
            StringBuffer qbuffer = null;
            if (query != null) {
                String[] qs = query.split("&");
                for (String q : qs) {
                    String[] s = q.split("=");
                    if (name.equals(s[0])) {
                        q = "";
                    }
                    if (qbuffer == null) {
                        qbuffer = new StringBuffer();
                        qbuffer.append("?").append(q);
                    } else {
                        qbuffer.append("&").append(q);
                    }
                }
            }
            url = path + (qbuffer != null ? qbuffer.toString() : "");
        } catch (URISyntaxException e) {
            logger.debug(e.getMessage());
        }
        return url;
    }

    /**
     * 获取url的host路径
     * 如：http://www.taobao.com
     *
     * @param url
     * @return
     */
    public static String getHostPath(String url) {
        String path = null;
        try {
            URI uri = new URI(url);
            path = uri.getScheme() + "://" + uri.getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }
}

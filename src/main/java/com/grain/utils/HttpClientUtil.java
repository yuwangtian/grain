package com.grain.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/6/17.
 */
public class HttpClientUtil {
    private static final String CHARSET = "UTF-8";
    private static final int MAX_RETRY = 3;
    private static HttpClientBuilder clientBuilder;
    private static Logger logger = Logger.getLogger(HttpClientUtil.class);

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(50);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10 * 60 * 1000).setConnectTimeout(60 * 1000).setConnectionRequestTimeout(10 * 1000).build();
        clientBuilder = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig);
        clientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(MAX_RETRY, true));
        clientBuilder.setRedirectStrategy(new LaxRedirectStrategy());
        IdleConnectionMonitorThread monitor = new IdleConnectionMonitorThread(cm);
        monitor.start();
    }

    public static HttpClient getHttpClient() {
        return clientBuilder.build();
    }

    public static String post(String url, String params, Map<String, String> headers) {
        String body = null;
        HttpPost post = new HttpPost(url);
        if (StringUtils.isNotBlank(params)) {
            post.setEntity(new StringEntity(params, CHARSET));
        }
        post.addHeader("Accept", "*/*");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                Header header = new BasicHeader(entry.getKey(), entry.getValue());
                post.addHeader(header);
            }
        }
        HttpClient client = getHttpClient();
        try {
            HttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                body = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } else {
                logger.error("response status error.status code:" + statusCode + ",url:" + url);
            }
        } catch (Exception e) {
            logger.error("request error.request url:" + url, e);
        } finally {
            post.releaseConnection();
        }
        return body;
    }

    public static String post(String url) {
        return post(url, null);
    }

    public static String post(String url, String params) {
        return post(url, params, new HashMap<String, String>());
    }

    public static String post(String url, String params, String cookie) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", cookie);
        return post(url, params, headers);
    }

    public static String get(String url) {
        String body = null;
        HttpGet get = new HttpGet(url);
        HttpClient client = getHttpClient();
        try {
            HttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                body = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } else {
                logger.error("response status error.status code:" + statusCode + ",url:" + url);
            }
        } catch (Exception e) {
            logger.error("request error.request url:" + url, e);
        } finally {
            get.releaseConnection();
        }
        return body;
    }

    public static class IdleConnectionMonitorThread extends Thread {
        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
            super();
            this.setDaemon(true);
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        // Close expired connections
                        connMgr.closeExpiredConnections();
                        /*
                        // Optionally, close connections
                        // that have been idle longer than 30 sec
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                        */
                    }
                }
            } catch (InterruptedException ex) {
                this.interrupt();
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }
}
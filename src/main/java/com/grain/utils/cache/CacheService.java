package com.grain.utils.cache;

import com.grain.utils.DateUtils;
import com.grain.utils.PropertiesUtil;
import com.grain.utils.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Properties;


/**
 * 缓存框架REDIS
 *
 * @author yuchen
 * @date 2015-1-14
 */
@Service
public class CacheService {

    /**
     * slf4j 日志
     */
    private static final Logger logger = LoggerFactory
            .getLogger(CacheService.class);

    private static JedisPool pool = null;
    private static Properties properties = PropertiesUtil
            .loadProperties("/config.properties");

    private static String REDIS_SERVER_IP = properties
            .getProperty("redis.server.ip");

    private static String REDIS_SERVER_PORT = properties
            .getProperty("redis.server.port");

    /**
     * 构建redis连接池
     *
     * @return JedisPool
     */
    public static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            //config.setMaxActive(500);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            // config.setMaxIdle(5);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            //  config.setMaxWait(1000 * 100);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
//            String host = PropertiesConfigFileUtil.readValue("JedisPoolUrl");
            // int port = 30002;
//            if(PropertiesConfigFileUtil.readValue("JedisPoolPort")!=null){
//                port = new Integer(PropertiesConfigFileUtil.readValue("JedisPoolPort")).intValue();
//            }
//            int timeOut = 2000;
//            if(PropertiesConfigFileUtil.readValue("JedisPoolOutTime")!=null){
//                timeOut = new Integer(PropertiesConfigFileUtil.readValue("JedisPoolOutTime")).intValue();
//            }
//            pool = new JedisPool(config, host, port, timeOut);
            //System.out.println("-----------------------------"+REDIS_SERVER_IP);
            pool = new JedisPool(config, REDIS_SERVER_IP, Integer.parseInt(REDIS_SERVER_PORT), 2000);
        }
        return pool;
    }

    /**
     * 返还到连接池
     *
     * @param pool
     * @param redis
     */
    public static void returnResource(JedisPool pool, Jedis redis) {
        if (redis != null) {
            pool.returnResource(redis);
        }
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public static Object get(String key,HttpServletRequest request) {

        return request.getSession().getAttribute(key);
    }

    /**
     * 存储数据
     *
     * @param key
     * @return
     */
    public static void set(String key, Object value,HttpServletRequest request) {
         request.getSession().setAttribute(key,value);
    }

    /**
     * 删除数据
     *
     * @param key
     * @return
     */
    public static void del(String key,HttpServletRequest request) {
        request.getSession().removeAttribute(key);
    }

    /**
     * 可以在凌晨删除所有缓存中的数据
     *
     * @return
     */
    public static void cleanAll() {


    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        //CacheService.set("ddd", "mmmm");
        // CacheService.get("25425879CF0DDBA3F65C5AAE0C55CC94_loginUser");

        // CacheService.get("25425879CF0DDBA3F65C5AAE0C55CC94_dqDay");
        // CacheService.get("ddd");
        //CacheService.get("ddd");
        //
//        CacheService.cleanAll();

    }

    /**
     * 清除缓存
     * @param name
     * @return
     */
    public void clearCache(String name,HttpServletRequest request) {
        CacheService.del(name,request);
    }

    /**
     * 为了让集群中的机器不做tomcat应用服务器之间的session复制，
     * 而是将session放在另外一台缓存服务器中
     *
     * @param request
     * @param name
     * @return
     */
    public Object setSession2Cache(HttpServletRequest request, String name, Object obj) {

        try {
            //为了让集群中的机器不做tomcat应用服务器之间的session复制，而是将session放在另外一台缓存服务器中
            String sessionId = request.getRequestedSessionId();
            HttpSession session = request.getSession(true);

            String key = sessionId + "_" + name;
            String newKey = session.getId() + "_" + name;
            if("beginDate".equals(name)){
                newKey =  name;
                key=name;
            }
            Object sessionObj = session.getAttribute(name);
            if (!"".equals(name) && obj != null) {
                session.setAttribute(name, obj);
                CacheService.set(newKey, obj,request);
                sessionObj = obj;
            }
            //logger.debug("------1-----key-------------------------setSession2Cache:"+key);
            //logger.debug("------2-----newKey-------------------------setSession2Cache:"+newKey);
            //logger.debug("-------3----name-------------------------setSession2Cache:"+name);
            //logger.debug("-------3----sessionObj-------------------------setSession2Cache:"+sessionObj);
            //处理超时时间
            String startT = (String) CacheService.get(session.getId(),request);
            ////logger.debug("-------4----startT-------------------------setSession2Cache:"+startT);
            if (startT != null) {
                Date date1 = DateUtils.getDateByString(startT, "yyyy-MM-dd HH:mm:ss");
                Date date2 = new Date();
                long t1 = date1.getTime() / 1000;
                long t2 = date2.getTime() / 1000;
                //logger.debug("-----5------t1-------------------------setSession2Cache:"+t1);
                long t = t2 - t1;
                //logger.debug("------6-----t2-------------------------setSession2Cache:"+t2);
                //logger.debug("------7-----t-------------------------setSession2Cache:"+t);
                if (t > 5400 && sessionObj == null) {
                    CacheService.del(key,request);
                    //logger.debug("------8-----del------------------------setSession2Cache:"+(t > 3600));
                    return null;
                }
            }
            //记录用户操作开始时间
            String currentTime = DateUtils.getCurrentDateTime();
            CacheService.set(session.getId(), currentTime,request);
            //logger.debug("---------9--currentTime------------------------setSession2Cache:"+currentTime);
            //第一次登陆时候obj不为空 放在session和缓存中
            if (!"".equals(name) && obj != null) {
                session.setAttribute(name, obj);
                CacheService.set(newKey, obj,request);
                //当宕机以后重新登录，删除以前的session
                if (!key.equals(newKey))
                    CacheService.del(key,request);
            }
            //logger.debug("--------10---sessionObj------------------------setSession2Cache:"+sessionObj);
            if (obj == null && sessionObj != null) {
                obj = sessionObj;
            }
            //首先从session中获取，如果session中没有，可能是本台机器宕机了，
            //如果本台机器宕机
            //宕机的情况下，新用户登录另外一台机器，存储到新的session
            if (obj != null) {
                //logger.debug("----11-------obj------------------------setSession2Cache:"+obj);
                //宕机的情况下，并且是新用户登录另外一台机器，把当前登录信息存储到新机器的session中
                session.setAttribute(name, obj);
                CacheService.set(newKey, obj,request);
            } else {
                Object cacheObj = CacheService.get(key,request);
                //logger.debug("------12-----cacheObj------------------------setSession2Cache:"+cacheObj);
                if (cacheObj != null) {
                    obj = cacheObj;
                }
                //FBAECFD3CA01CCF46AEA2EE770AA837C_loginUser
                //宕机的情况下，不是新登录的情况下，obj==null，并且缓存中有记录，就从缓存中获取session的数据
                //并且存储到新机器的session中
                ////logger.debug("------13-----obj------------------------setSession2Cache:"+obj);
                if (obj != null) {
                    //logger.debug("------14-----obj------------------------setSession2Cache:"+obj);
                    session.setAttribute(name, obj);
                } else {
                    //logger.debug("------15-----obj------------------------setSession2Cache:"+obj);
                    //缓存和session都没有
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}

package com.grain.utils.cache;

import com.grain.utils.SerializeUtil;
import com.grain.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;


/**
 * 缓存框架REDIS
 *
 * @author yuchen
 * @date 2015-1-14
 */
@Service
public class CacheClearService {

    /**
     * slf4j 日志
     */
    private static final Logger logger = LoggerFactory
            .getLogger(CacheClearService.class);

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
            logger.info(REDIS_SERVER_IP);
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
    public static Object get(String key) {
        Object value = null;

        JedisPool pool = null;
        Jedis jedis = null;
        try {

            pool = getPool();
            jedis = pool.getResource();
            // //logger.debug("jedis=" + jedis);
            // value = jedis.get(key);

            byte[] obj = jedis.get(key.getBytes());
            value = SerializeUtil.unserialize(obj);


            //logger.debug("Redis--set");
            //logger.debug("key=======" + key);
            //logger.debug("value=====" + value);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }

        return value;
    }

    /**
     * 存储数据
     *
     * @param key
     * @return
     */
    public static void set(String key, Object value) {

        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.set(key.getBytes(), SerializeUtil.serialize(value));
            //   //logger.debug("Redis--set");
            //logger.debug("key=======" + key);
            //logger.debug("value=====" + value);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 删除数据
     *
     * @param key
     * @return
     */
    public static void del(String key) {

        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.del(key.getBytes());
            //logger.debug("key=======" + key);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
    }

    /**
     * 可以在凌晨删除所有缓存中的数据
     *
     * @return
     */
    public static void cleanAll() {

        JedisPool pool = null;
        Jedis jedis = null;
        try {
            logger.info("开始清理.....");
            pool = getPool();
            jedis = pool.getResource();
            jedis.flushAll();
            logger.info("清理完毕.....");
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
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
        CacheClearService.cleanAll();

    }


}

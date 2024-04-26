package com.selfstudy.common.utils;

/**
 * Redis所有Keys
 *
 * @author Mark 2891517520@qq.com
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}

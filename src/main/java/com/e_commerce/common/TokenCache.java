package com.e_commerce.common;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by code on 3/25/18.
 */
public class TokenCache {
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";

    //LRU
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000)
            .expireAfterAccess(12, TimeUnit.HOURS).build(new CacheLoader<String, String>() {
                //default data loading implementation, when use get to get value, if key does not have corresponding
                //value, use this method to laod
                @Override
                public String load(String key) throws Exception {
                    return null;
                }
            });

    public static void setKey(String key, String value){
        localCache.put(key,value);
    }
    public static long getSize(){
        return localCache.size();
    }
    public static String getKey(String key){
        String value = null;
        try{
            value = localCache.get(key);
            if("null".equals(value)){
                return null;
            }
            return value;
        }catch (Exception e){
            logger.error("localCache get error", e);
        }
        return null;
    }
}

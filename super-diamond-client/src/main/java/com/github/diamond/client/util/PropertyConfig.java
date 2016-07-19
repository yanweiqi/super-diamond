package com.github.diamond.client.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;

/**
 * Created by yanweiqi on 2016/6/30.
 */
@Named
public class PropertyConfig {

    private static final Logger log = LoggerFactory.getLogger(PropertyConfig.class);

    private static AbstractBeanFactory beanFactory = null;

    private static final Map<String,String> cache = new ConcurrentHashMap<>();

    @Inject
    public PropertyConfig(AbstractBeanFactory beanFactory) {
        PropertyConfig.beanFactory = beanFactory;
    }

    /**
     * 根据key获取配置文件的Value
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        String propValue = "";
        if(cache.containsKey(key)){
            propValue = cache.get(key);
        } else {
            try {
                propValue = beanFactory.resolveEmbeddedValue("${" + key.trim() + "}");
                cache.put(key,propValue);
            } catch (IllegalArgumentException ex) {
                log.error("${" + key.trim() + "}的值不存在");
            }
        }
        return propValue;
    }
}
package com.togo.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author taiyn
 * @Description 配置读取工具
 * @Date 5:23 下午 2020/4/5
 **/
@Slf4j
public final class ConfigUtil {

    private static ConfigUtil configUtil = new ConfigUtil();
    private Properties properties;
    private static final String configName = "application.properties";

    private ConfigUtil() {
    }

    public static ConfigUtil instance() {

        return configUtil;
    }

    /**
     * @return void
     * @Author taiyn
     * @Description 初始化配置
     * @Date 5:40 下午 2020/4/5
     * @Param []
     **/
    public void init() {

        properties = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {

                log.error("failed to read properties: [{}]", configName, e);
            }
        }
    }

    /**
     * @return java.lang.String
     * @Author taiyn
     * @Description 读取配置信息
     * @Date 5:40 下午 2020/4/5
     * @Param [key]
     **/
    public String read(String key) {

        if (properties == null) {
            log.error("failed to read key [{}] from properties: [{}]", key, configName);
            throw new NullPointerException("properties is null");
        }

        log.info(properties.getProperty(key));

        return properties.getProperty(key);
    }

    public int readInt(String key) {

        String value = read(key);
        return Integer.parseInt(value);
    }
}

package com.yin.practice.simplespring.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author yinxunzhi
 * @date 2018/9/3 下午3:17
 */
public class ClassPathResourceDemo {

    private static final String DEFAULT_STRATEGIES_PATH = "ContextLoader.properties";


    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource(DEFAULT_STRATEGIES_PATH, ClassPathResourceDemo.class);
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
    }
}

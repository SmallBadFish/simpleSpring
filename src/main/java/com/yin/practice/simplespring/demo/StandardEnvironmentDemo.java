package com.yin.practice.simplespring.demo;

import org.springframework.core.env.StandardEnvironment;

import java.util.Map;

/**
 * @author yinxunzhi
 * @date 2018/9/5 上午11:56
 */
public class StandardEnvironmentDemo {

    public static void main(String[] args) {
        StandardEnvironment environment = new StandardEnvironment();
//        String[] defaultProfiles = environment.getDefaultProfiles();
//        for (String profile : defaultProfiles) {
//            System.out.println(profile);
//        }

//        Map<String, Object> systemProperties = environment.getSystemProperties();
//        for (Map.Entry<String, Object> property : systemProperties.entrySet()) {
//            System.out.println("key:-->" + property.getKey() + "\t value:-->" + property.getValue());
//        }

        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        for (Map.Entry<String, Object> env : systemEnvironment.entrySet()) {
            System.out.println("key:-->" + env.getKey() + "\t value:-->" + env.getValue());
        }

    }
}

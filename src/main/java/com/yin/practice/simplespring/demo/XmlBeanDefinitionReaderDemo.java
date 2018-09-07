package com.yin.practice.simplespring.demo;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author yinxunzhi
 * @date 2018/9/7 上午10:58
 */
public class XmlBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new DefaultListableBeanFactory());
        reader.loadBeanDefinitions("classpath:application.xml");
    }
}

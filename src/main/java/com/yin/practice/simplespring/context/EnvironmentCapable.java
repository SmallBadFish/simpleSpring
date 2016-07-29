package com.yin.practice.simplespring.context;

import com.yin.practice.simplespring.core.env.Environment;

/**
 * 实现该接口表示可以获取到当前的Environment
 *
 */
public interface EnvironmentCapable {

	Environment getEnvironment();
}

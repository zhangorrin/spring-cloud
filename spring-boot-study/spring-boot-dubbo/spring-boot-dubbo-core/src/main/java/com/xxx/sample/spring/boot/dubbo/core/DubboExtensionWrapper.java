package com.xxx.sample.spring.boot.dubbo.core;

/**
 * Created by orrin on 2017/3/3/003.
 */
public interface DubboExtensionWrapper<T> {

    Class getExtensionClass();

    T getDefaultExtension();
}

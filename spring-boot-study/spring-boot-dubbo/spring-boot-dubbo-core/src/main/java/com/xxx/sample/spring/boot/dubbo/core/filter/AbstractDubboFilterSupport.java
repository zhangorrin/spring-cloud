package com.xxx.sample.spring.boot.dubbo.core.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.xxx.sample.spring.boot.dubbo.core.AbstractDubboExtensionWrapper;

/**
 * Created by orrin on 2017/3/3/003.
 */

public abstract class AbstractDubboFilterSupport extends AbstractDubboExtensionWrapper<Filter> implements Filter {


    public AbstractDubboFilterSupport() {
        super(Filter.class);
    }


}

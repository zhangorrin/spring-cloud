package com.xxx.sample.spring.boot.dubbo.core.filter;

import com.alibaba.dubbo.rpc.*;

/**
 * Created by orrin on 2017/3/3/003.
 */
public class AbstractActiveFilterWrapper implements Filter {
    AbstractDubboFilterSupport abstractDubboFilterSupport;

    public AbstractActiveFilterWrapper(AbstractDubboFilterSupport abstractDubboFilterSupport) {
        this.abstractDubboFilterSupport = abstractDubboFilterSupport;
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return abstractDubboFilterSupport.invoke(invoker, invocation);
    }
}

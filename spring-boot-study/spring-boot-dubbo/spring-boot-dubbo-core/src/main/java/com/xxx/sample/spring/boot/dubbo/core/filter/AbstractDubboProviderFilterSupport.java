package com.xxx.sample.spring.boot.dubbo.core.filter;


import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;

/**
 * Created by orrin on 2017/3/3/003.
 */
public abstract class AbstractDubboProviderFilterSupport extends AbstractDubboFilterSupport {

    public Filter getDefaultExtension() {
        return new ProviderActiveFilter(this);
    }

    @Activate(group = Constants.PROVIDER)
    static class ProviderActiveFilter extends AbstractActiveFilterWrapper {

        ProviderActiveFilter(AbstractDubboFilterSupport abstractDubboFilterSupport) {
            super(abstractDubboFilterSupport);
        }
    }
}

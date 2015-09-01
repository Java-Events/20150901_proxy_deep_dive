package org.rapidpm.event.opench2015.v003.p05;

import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * @author Dominik Menzi
 */
public class SecureVirtualProxy implements Service {

    private final Service delegate;

    public SecureVirtualProxy() {
        final ThreadSaveServiceStrategyFactory strategyFactory = new ThreadSaveServiceStrategyFactory();
        final OnDemandService onDemandService = new OnDemandService(strategyFactory);
        delegate = new ServiceSecurityProxy(onDemandService);
    }

    @Override
    public String work(String txt) {
        return delegate.work(txt);
    }
}

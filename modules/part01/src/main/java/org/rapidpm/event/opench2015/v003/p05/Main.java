package org.rapidpm.event.opench2015.v003.p05;

import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * @author Dominik Menzi
 */
public class Main {

    public static void main(String... args) {
        final Service staticProxy = new SecureVirtualProxy();

        final String work = staticProxy.work("hello");
        System.out.println(work);

        final ThreadSaveServiceStrategyFactory strategyFactory = new ThreadSaveServiceStrategyFactory();
        final OnDemandService onDemandService = new OnDemandService(strategyFactory);
        final ServiceSecurityProxy iocProxy = new ServiceSecurityProxy(onDemandService);

        iocProxy.setAccessCode("hoppel");
        System.out.println(iocProxy.work("huhu"));
    }
}

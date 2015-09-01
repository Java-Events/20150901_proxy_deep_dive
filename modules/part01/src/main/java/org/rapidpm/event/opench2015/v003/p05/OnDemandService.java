package org.rapidpm.event.opench2015.v003.p05;

import org.rapidpm.event.opench2015.proxy.model.Service;
import org.rapidpm.event.opench2015.proxy.model.ServiceImpl;
import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version01_01.ServiceFactory;
import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version01_01.ServiceStrategyFactory;

/**
 * @author Dominik Menzi
 */
class OnDemandService implements Service {

    private final ServiceStrategyFactory strategyFactory;
    private ServiceFactory serviceFactory = ServiceImpl::new;

    public OnDemandService(ServiceStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    @Override
    public String work(String txt) {
        return strategyFactory.realSubject(serviceFactory).work(txt);
    }
}

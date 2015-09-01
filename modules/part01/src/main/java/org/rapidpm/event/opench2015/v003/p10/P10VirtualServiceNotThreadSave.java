package org.rapidpm.event.opench2015.v003.p10;

import org.rapidpm.event.opench2015.proxy.model.Service;
import org.rapidpm.event.opench2015.proxy.model.ServiceImpl;
import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version01_01.ServiceFactory;
import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version01_01.ServiceStrategyFactory;
import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version01_01.ServiceStrategyFactoryNotThreadSave;

/**
 * Created by Sven Ruppert on 15.09.2014.
 */
public class P10VirtualServiceNotThreadSave implements Service {

    private P10ServiceFactory serviceFactory = ServiceImpl::new;

    private P10ServiceStrategyFactory strategyFactory
            = new P10ServiceStrategyFactoryNotThreadSave();

    @Override
    public String work(String txt) {
        return strategyFactory.realSubject(serviceFactory).work(txt);
    }
}

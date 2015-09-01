package org.rapidpm.event.opench2015.v003.p05;

import org.rapidpm.event.opench2015.proxy.model.Service;
import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version01_01.ServiceFactory;
import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version01_01.ServiceStrategyFactory;

public class ThreadSaveServiceStrategyFactory implements ServiceStrategyFactory {

    Service realSubject;

    @Override
    public synchronized Service realSubject(final ServiceFactory factory) {
        if (realSubject == null) {
            realSubject = factory.createInstance();
        }
        return realSubject;
    }
}

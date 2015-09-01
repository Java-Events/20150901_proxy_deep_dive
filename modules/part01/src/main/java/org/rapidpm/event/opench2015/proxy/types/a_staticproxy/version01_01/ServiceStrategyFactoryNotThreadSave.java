package org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version01_01;


import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * Created by sven on 13.04.15.
 */
public class ServiceStrategyFactoryNotThreadSave implements ServiceStrategyFactory {

    Service realSubject;

    @Override
    public Service realSubject(final ServiceFactory factory) {
        if (realSubject == null) {
            realSubject = factory.createInstance();
        }
        return realSubject;
    }
}

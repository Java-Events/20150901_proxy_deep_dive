package org.rapidpm.event.opench2015.v003.p10;


import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * Created by sven on 13.04.15.
 */
public class P10ServiceStrategyFactoryNotThreadSave implements P10ServiceStrategyFactory {

    Service realSubject;

    @Override
    public Service realSubject(final P10ServiceFactory factory) {
        if (realSubject == null) {
            realSubject = factory.createInstance();
        }
        return realSubject;
    }
}

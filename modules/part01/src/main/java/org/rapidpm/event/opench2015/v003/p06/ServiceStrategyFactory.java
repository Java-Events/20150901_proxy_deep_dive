package org.rapidpm.event.opench2015.v003.p06;


import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version01_01.*;
import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * Strategy of creating: ThreadSave, NotThreadSave, ...
 */
public interface ServiceStrategyFactory {
    Service realSubject(ServiceFactory factory);
}

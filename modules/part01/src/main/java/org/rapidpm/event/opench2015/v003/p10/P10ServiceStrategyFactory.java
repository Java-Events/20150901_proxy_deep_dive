package org.rapidpm.event.opench2015.v003.p10;


import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * Strategy of creating: ThreadSave, NotThreadSave, ...
 */
public interface P10ServiceStrategyFactory {
    Service realSubject(P10ServiceFactory factory);
}

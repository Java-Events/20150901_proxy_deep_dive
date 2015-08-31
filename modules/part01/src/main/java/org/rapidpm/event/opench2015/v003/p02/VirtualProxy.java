package org.rapidpm.event.opench2015.v003.p02;

import org.rapidpm.event.opench2015.proxy.model.Service;
import org.rapidpm.event.opench2015.proxy.model.ServiceImpl;

/**
 * Created by svenruppert on 01.09.15.
 */
public class VirtualProxy implements Service{

    private Service service;

    @Override
    public String work(String txt) {
        if (service == null){
            service = new ServiceImpl();
        }
        return service.work(txt);
    }
}

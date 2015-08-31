package org.rapidpm.event.opench2015.v003.p02;

import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * Created by svenruppert on 01.09.15.
 */
public class SecureVirtualProxy implements Service{

    private final VirtualProxy virtualProxy = new VirtualProxy();
    private final SecureProxy secureProxy = new SecureProxy();

    public SecureVirtualProxy() {
        secureProxy.setService(virtualProxy);
    }

    @Override
    public String work(String txt) {
        return secureProxy.work(txt);
    }
}

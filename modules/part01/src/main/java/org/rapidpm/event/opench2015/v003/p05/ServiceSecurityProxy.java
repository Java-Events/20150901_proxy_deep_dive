package org.rapidpm.event.opench2015.v003.p05;

import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * @author Dominik Menzi
 */
public class ServiceSecurityProxy implements Service {

    private final Service service;

    private String accessCode;

    public ServiceSecurityProxy(Service service) {
        this.service = service;
    }

    //Simmulation der Tastatureingabe
    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    @Override
    public String work(String txt) {
        if (hasPermission()) {
            return service.work(txt);
        }
        else {
            return "nooooop";
        }
    }

    private boolean hasPermission() {
        return "hoppel".equals(accessCode);
    }
}

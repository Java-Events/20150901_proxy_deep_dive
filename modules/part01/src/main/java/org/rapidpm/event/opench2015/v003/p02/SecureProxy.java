package org.rapidpm.event.opench2015.v003.p02;

import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * Created by svenruppert on 01.09.15.
 */
public class SecureProxy implements Service{

    private Service service;

    private String code = "";

    //Simmulation der Tastatureingabe
    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String work(String txt) {
        if(!code.equals("hoppel")){
            throw new IllegalStateException("code must be hoppel but was "+ code);
        }
        return  service.work(txt);
    }

    public void setService(Service service) {
        this.service = service;
    }
}

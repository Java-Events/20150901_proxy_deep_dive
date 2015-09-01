package org.rapidpm.event.opench2015.v003.p10;

import org.rapidpm.event.opench2015.proxy.model.Service;
import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version02.ServiceSecurityProxy;

/**
 * Created by buergich on 01.09.15.
 */
public class P10MainV003 {

    public static void main(String[] args) {

        Service service = new P10ServiceSecurityProxy();

        ((P10ServiceSecurityProxy)service).setCode("Nase");  //Eingabe simulieren
        System.out.println(service.work("Hallo"));

        ((P10ServiceSecurityProxy)service).setCode("hoppel"); //Eingabe simulieren
        System.out.println(service.work("Hallo"));
    }
}

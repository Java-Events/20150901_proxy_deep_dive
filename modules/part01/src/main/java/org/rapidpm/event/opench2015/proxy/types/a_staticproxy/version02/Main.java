package org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version02;


import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * Created by Sven Ruppert on 22.09.2014.
 */
public class Main {
  public static void main(String[] args) {
    Service proxy = new ServiceSecurityProxy();

    ((ServiceSecurityProxy)proxy).setCode("Nase");  //Eingabe simulieren
    System.out.println(proxy.work("Hallo"));

    ((ServiceSecurityProxy)proxy).setCode("hoppel"); //Eingabe simulieren
    System.out.println(proxy.work("Hallo"));
  }
}

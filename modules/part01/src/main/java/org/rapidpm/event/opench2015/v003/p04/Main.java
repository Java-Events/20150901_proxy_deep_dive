package org.rapidpm.event.opench2015.v003.p04;


import org.rapidpm.event.opench2015.proxy.model.Service;

/**
 * Created by Sven Ruppert on 22.09.2014.
 */
public class Main {
  public static void main(String[] args) {
    Service proxy = new SecureVirtualProxy();

    ((SecureVirtualProxy)proxy).setCode("Nase");  //Eingabe simulieren
    System.out.println(proxy.work("Hallo"));

    ((SecureVirtualProxy)proxy).setCode("hoppel"); //Eingabe simulieren
    System.out.println(proxy.work("Hallo"));
  }
}

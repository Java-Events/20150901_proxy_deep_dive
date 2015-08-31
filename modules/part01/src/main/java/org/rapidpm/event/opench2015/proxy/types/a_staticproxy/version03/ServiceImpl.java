package org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version03;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by Sven Ruppert on 22.09.2014.
 */
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService(endpointInterface = "org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version03.Service")
public class ServiceImpl implements Service {
  @Override
  public String work(String txt) {
//    System.out.println("remote txt = " + txt);
    return "ServiceImpl - " + txt;
  }
}

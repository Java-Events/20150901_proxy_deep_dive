package org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version02;

import org.rapidpm.event.opench2015.proxy.model.Service;
import org.rapidpm.event.opench2015.proxy.model.ServiceImpl;

/**
 *
 *
 *
 * Created by Sven Ruppert on 22.09.2014.
 */
public class ServiceSecurityProxy implements Service {

  private Service service = new ServiceImpl();

  private String code = "";

  //Simmulation der Tastatureingabe
  public void setCode(String code) {
    this.code = code;
  }

  public String work(String txt) {
    if(code.equals("hoppel")){
      return service.work(txt);
    } else{
      return "nooooop";
    }
  }
}

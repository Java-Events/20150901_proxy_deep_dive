package org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version00;

import org.rapidpm.event.opench2015.proxy.model.Service;
import org.rapidpm.event.opench2015.proxy.model.ServiceImpl;

/**
 * Kein Unterschied zum Delegator!!
 * <p>
 * Created by Sven Ruppert on 22.09.2014.
 */
public class ServiceProxy implements Service {
  private Service service = new ServiceImpl();

  public String work(String txt) {
    return service.work(txt);
  }
}

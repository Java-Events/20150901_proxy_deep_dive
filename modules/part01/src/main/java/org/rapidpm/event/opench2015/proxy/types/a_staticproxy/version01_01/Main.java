package org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version01_01;


import org.rapidpm.event.opench2015.proxy.model.Service;
import org.rapidpm.event.opench2015.proxy.model.ServiceImpl;

/**
 * Created by sven on 30.04.15.
 */
public class Main {

  public static void main(String[] args) {
    Service service = new ServiceProxy();
    final String huHU = service.work("HuHU");
    System.out.println("huHU = " + huHU);
  }


  public static class ServiceProxy implements Service {

    //    private ServiceFactory serviceFactory = ServiceImpl::new;
    private ServiceFactory serviceFactory = new ServiceFactory() {
      @Override
      public Service createInstance() {
        return new ServiceImpl();
      }
    };


    private ServiceStrategyFactory strategyFactory
        = new ServiceStrategyFactoryNotThreadSave();

    @Override
    public String work(String txt) {
      return strategyFactory.realSubject(serviceFactory).work(txt);
    }
  }


}

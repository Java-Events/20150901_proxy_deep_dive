package org.rapidpm.event.opench2015.v003;

import org.rapidpm.event.opench2015.proxy.model.Service;
import org.rapidpm.event.opench2015.proxy.model.ServiceImpl;

import java.lang.reflect.Field;

/**
 * Created by svenruppert on 01.09.15.
 */
public class Main {


  public static void main(String[] args) {
    final Service service = SecProxyFactory
        .create(new ServiceImpl());
    System.out.println("=> " + service.work("huhu"));
  }

  public static class SecProxy implements Service {

    private Service service;

//    public SecProxy(final Service service) {
//      this.service = service;
//    }
//    public void setService(final Service service) {
//      this.service = service;
//    }
//    @Inject Service service;

    @Override
    public String work(final String txt) {
      if(true) return  service.work(txt);
      return null;
    }
  }

  public static class SecProxyFactory {

    public static Service create(Service service){
      final SecProxy secProxy = new SecProxy();
      //refl set Service
      try {
        final Field declaredField = SecProxy.class
            .getDeclaredField("service");
        final boolean accessible = declaredField.isAccessible();
        declaredField.setAccessible(true);
        declaredField.set(secProxy, service);
        declaredField.setAccessible(accessible);

      } catch (NoSuchFieldException | IllegalAccessException e) {
        e.printStackTrace();
      }


      return secProxy;
    }
  }




}

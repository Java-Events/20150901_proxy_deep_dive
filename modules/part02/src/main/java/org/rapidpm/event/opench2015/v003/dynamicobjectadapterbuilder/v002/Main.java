package org.rapidpm.event.opench2015.v003.dynamicobjectadapterbuilder.v002;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by sven on 12.05.15.
 */
public class Main {

  public static void main(String[] args) {


    Service service = ServiceAdapterBuilder.newBuilder()
        .setOriginal(new ServiceImpl())
        .withDoWork_A((txt) -> txt + "_part")
//        .withTarget(Service.class) //leider als letztes.....
        .buildForTarget(Service.class);

    System.out.println(service.doWork_A("Hallo Adapter"));


    final boolean proxyClass = Proxy.isProxyClass(service.getClass());
    System.out.println("proxyClass = " + proxyClass);

    //Interface auf den InvocactionHandler
    final InvocationHandler invocationHandler = Proxy.getInvocationHandler(service);
    final ServiceInvocationHandler serviceInvocationHandler = (ServiceInvocationHandler) invocationHandler;

    serviceInvocationHandler.withDoWork_A((txt) -> txt + "_part_modified");
    System.out.println(service.doWork_A("Hallo Adapter"));

  }


  //generieren ?
  public interface ServiceAdapter_A {
    String doWork_A(String txt);
  }

  public interface ServiceAdapter_B {
    String doWork_B(String txt);
  }


  //manuell - typesave
  public static class ServiceInvocationHandler
      extends ExtendedInvocationHandler<Service> {

    public void withDoWork_A(ServiceAdapter_A adapter) {
      addAdapter(adapter);
    }

    public void withDoWork_B(ServiceAdapter_B adapter) {
      addAdapter(adapter);
    }
  }


  public static class ServiceAdapterBuilder extends AdapterBuilder<Service> {

    public static ServiceAdapterBuilder newBuilder() {
      return new ServiceAdapterBuilder();
    }

    private final ServiceInvocationHandler invocationHandler
        = new ServiceInvocationHandler();

    //evtl auch statisch initialisiert, wenn gewuenscht
//    {
//      invocationHandler.setOriginal(new ServiceImpl());
//    }

//delegate , modify
    public ServiceAdapterBuilder withDoWork_A(ServiceAdapter_A adapter) {
      invocationHandler.withDoWork_A(adapter);
      return this;
    }

    public ServiceAdapterBuilder withDoWork_B(ServiceAdapter_B adapter) {
      invocationHandler.withDoWork_B(adapter);
      return this;
    }

    public ServiceAdapterBuilder setOriginal(Service original) {
      invocationHandler.setOriginal(original);
      return this;
    }

    @Override
    protected  ServiceInvocationHandler getInvocationHandler() {
      return invocationHandler;
    }
  }


}

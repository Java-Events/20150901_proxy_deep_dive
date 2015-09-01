package org.rapidpm.event.opench2015.v002;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by svenruppert on 01.09.15.
 */
public class MainV002_001 {

  public static void main(String[] args) {

    Service serviceA = new ServiceImpl();
    Service serviceB = new Proxy();


  }

  public static class Proxy implements Service{
    private Service service = new ServiceImpl();//TODO

    public String doWork(Collection<String> txt) {
      //TODO Collection -> String...
      return service.doWork(null);
      //TODO ???
    }
  }

  public static interface Service{
    public String doWork(Collection<String> txt);
  }

  public static class ServiceImpl implements Service{
    public String doWork(Collection<String> txt){
      return LocalDateTime.now().toString();
    }
  }


}

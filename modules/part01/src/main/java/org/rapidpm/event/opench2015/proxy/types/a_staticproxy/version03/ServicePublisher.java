package org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version03;

import javax.xml.ws.Endpoint;
import java.util.concurrent.Executors;

/**
 * publish to http://localhost:9999/ws/service?wsdl
 *
 * Created by Sven Ruppert on 22.09.2014.
 */

public class ServicePublisher {
  public static void main(String[] args) {
    //single Threaded Endpoint.publish("http://localhost:9999/ws/service", new ServiceImpl());

    final String address = "http://localhost:9999/ws/service";
    final Endpoint endpoint = Endpoint.create(new ServiceImpl());
    endpoint.setExecutor(Executors.newFixedThreadPool(10));

    endpoint.publish(address);

    System.out.println("endpoint = " + endpoint.isPublished());

  }
}

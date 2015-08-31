package org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version03;

import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version03.remote.ServiceRemoteProxy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

/**
 * starte Endpoint zuerst !!
 *
 * Created by Sven Ruppert on 22.09.2014.
 */
public class Main {
  public static void main(String[] args) {
    Service proxy = new ServiceRemoteProxy();

    final LocalDateTime start = LocalDateTime.now();
    System.out.println("LocalDateTime.n.t = " + start.toString());
    IntStream.range(0,1_000_000).parallel().forEach(i-> proxy.work("Hello " + i));
    final LocalDateTime stop = LocalDateTime.now();
    System.out.println("LocalDateTime.n.t = " + stop.toString());

    final Duration between = Duration.between(start, stop);
    System.out.println("between = " + between);
//    System.out.println("proxy.work() = " + proxy.work("Hello"));
  }
}

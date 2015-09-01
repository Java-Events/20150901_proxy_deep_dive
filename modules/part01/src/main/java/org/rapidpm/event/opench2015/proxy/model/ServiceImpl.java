package org.rapidpm.event.opench2015.proxy.model;

/**
 * Created by Sven Ruppert on 22.09.2014.
 */
public class ServiceImpl implements Service {
  @Override public String work(String txt) {
    return "ServiceImpl - " + txt;
  }


}

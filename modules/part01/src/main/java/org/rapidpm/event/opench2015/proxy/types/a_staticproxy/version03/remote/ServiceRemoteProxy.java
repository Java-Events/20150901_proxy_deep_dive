package org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version03.remote;






import org.rapidpm.event.opench2015.proxy.types.a_staticproxy.version03.Service;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *   1st argument service URI, refer to wsdl document above
 *   2nd argument is service name, refer to wsdl document above
 * Created by Sven Ruppert on 22.09.2014.
 */
public class ServiceRemoteProxy implements Service {

  private URL url;
  private Service realSubject;

  public ServiceRemoteProxy() {
    try {
      url = new URL("http://localhost:9999/ws/service?wsdl");
      final String namespaceURI = "http://version03.a_staticproxy.types.proxy.opench2015.event.rapidpm.org/";
      final String localPart = "ServiceImplService";
      QName qname = new QName(namespaceURI, localPart);
      javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
      realSubject = service.getPort(Service.class);
//
//
//      service.
//      SOAPMessage soapMessage =  messageContext.getMessage();
//      SOAPPart soapPart = soapMessage.getSOAPPart();
//      SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
//      SOAPBody soapBody = soapEnvelope.getBody();
//      SOAPHeader soapHeader = soapEnvelope.getHeader();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }
  public String work(String txt){
    return realSubject.work(txt);
  }
}

package org.rapidpm.event.opench2015.v003.p06;

import org.rapidpm.event.opench2015.proxy.model.Service;
import org.rapidpm.event.opench2015.proxy.model.ServiceImpl;

/**
 * Created by sven on 30.04.15.
 */
public class Main {
    
    public static void main(String[] args) {
        Service service = new SecureVirtualServiceProxy();
        ((SecureVirtualServiceProxy)service).setCode("iamallowed");
        final String huHU = service.work("HuHU");
        System.out.println("huHU = " + huHU);
    }
    
    public static class SecureVirtualServiceProxy implements Service {
        
        private String code = "";
        
        public void setCode(String code) {
            this.code = code;
        }

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
            if (code.equals("iamallowed")) {
                return strategyFactory.realSubject(serviceFactory).work(txt);
            } else {
                throw new RuntimeException();
            }
        }
    }
    
}

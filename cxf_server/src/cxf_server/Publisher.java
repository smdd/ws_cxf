package cxf_server;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Publisher {
public static void main(String[] args) {
	
	HelloServiceImpl implementor = new HelloServiceImpl();
	JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
	factoryBean.setAddress("http://127.0.0.1:8181/hihi");
	factoryBean.setServiceClass(IHelloService.class);
	factoryBean.setServiceBean(implementor);
	System.out.println("================= add Logging Interceptor ====================");
	
	factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());

	factoryBean.getInInterceptors().add(new LoggingInInterceptor());
	factoryBean.getInInterceptors().add(new AuthentInfoInInterceptor("xiaohei","333"));
	
	factoryBean.create();
	
	System.out.println("publish ok");
	System.out.println("publish check git");
	
		
}
}

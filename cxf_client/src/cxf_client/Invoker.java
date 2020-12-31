package cxf_client;

import java.util.ArrayList;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import cxf_server.IHelloService;


public class Invoker {

	public static void main(String[] args) {
		
		JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
		
		bean.setAddress("http://127.0.0.1:8181/hihi");
		bean.setServiceClass(IHelloService.class);
		
		System.out.println("================= add LoggingOutInterceptor ====================");
		
		bean.getOutInterceptors().add(new LoggingOutInterceptor());
		bean.getOutInterceptors().add(new AuthentInterceptor("xiaohei","333"));
		
		bean.getInInterceptors().add(new LoggingInInterceptor());
		
		IHelloService helloService = (IHelloService) bean.create(IHelloService.class);
		System.out.println("--- ws info used by client ----- "+ helloService.sayHello("alibaba"));
	}
}

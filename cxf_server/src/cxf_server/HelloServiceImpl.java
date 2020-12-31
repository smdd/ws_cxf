package cxf_server;

import javax.jws.WebService;

@WebService(endpointInterface = "cxf_server.IHelloService")
public class HelloServiceImpl implements IHelloService {

	@Override
	public String sayHello(String name) {
		return name + " say hello";
	}

}

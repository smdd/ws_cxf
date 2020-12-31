package cxf_server;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

public class AuthentInfoInInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	private String username;
	private String password;
	
	public AuthentInfoInInterceptor(String username, String pwd) {
		super(Phase.PRE_INVOKE);
		this.username = username;
		this.password = pwd;
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		
		if(message.getHeaders().size()==0) {
			throw new IllegalArgumentException("请携带用户信息");
		}
			
		Header header = message.getHeaders().get(0);
		
		Element element = (Element)header.getObject();
		String name = element.getElementsByTagName("username").item(0).getTextContent();
		String pwd = element.getElementsByTagName("password").item(0).getTextContent();
		if(name.equals(this.username) && this.password.equals(pwd)) {
			System.out.println("authinfo ok");
			return;
		}else {
			throw new RuntimeException();
		}
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

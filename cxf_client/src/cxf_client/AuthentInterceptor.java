package cxf_client;


import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;

import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AuthentInterceptor extends AbstractPhaseInterceptor<SoapMessage>{

	private String username;
	private String password;
	
	
	public AuthentInterceptor(String username, String password) {
		super(Phase.PREPARE_SEND);
		this.username=username;
		this.password=password;
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
	
		QName qName = new QName("ss");
		Document document = DOMUtils.createDocument();
		Element authEle = document.createElement("authInfo");
		
		Element usernameEle = document.createElement("username");
		usernameEle.setTextContent(username);
		authEle.appendChild(usernameEle);
		
		Element passwordEle = document.createElement("password");
		passwordEle.setTextContent(password);
		authEle.appendChild(passwordEle);
		
		Header header = new Header(qName, authEle);
		message.getHeaders().add(header);
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

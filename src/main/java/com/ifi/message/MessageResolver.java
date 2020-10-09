package com.ifi.message;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ifi.stateless.HelloWorld;

public class MessageResolver {
	private HelloWorld helloWorld;

	public MessageResolver() throws NamingException {
		helloWorld = initBean();
	}

	private HelloWorld initBean() throws NamingException {
		return lookupBean();
	}

	private HelloWorld lookupBean() throws NamingException {
		Properties jndiProperties = buildProperties();
		Context context = new InitialContext(jndiProperties);
		return (HelloWorld) context.lookup("ejb:/ejb-remote-1.0/HelloWorld!com.ifi.stateless.HelloWorld");
	}

	private Properties buildProperties() {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		return jndiProperties;
	}

	public String getMessage() {
		return helloWorld.sayHello();
	}

	public String getMessage(String name) {
		return helloWorld.sayHello(name);
	}

}

package com.github.embedjetty;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;


public class EmbedJettyTest {
	private String contextPath = "/jetty";
	private String webAppRelativePath = "/src/test/resources/webapps/webapp";
	@Test
	public void testEmbedJettyStringString() {
		
		EmbedJetty jetty = new EmbedJetty(contextPath, webAppRelativePath);
		jetty.addServlet(contextPath, "/js", new HttpServlet() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void doGet(HttpServletRequest req,
					HttpServletResponse resp) throws ServletException,
					IOException {
				doPost(req, resp);
			}

			@Override
			protected void doPost(HttpServletRequest req,
					HttpServletResponse resp) throws ServletException,
					IOException {
				System.out.println("servlet post");
			}
			
		});
		
		jetty.start();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpUriRequest request = RequestBuilder.get().setUri("http://localhost:8080/jetty/js").build();
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.getStatusLine());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		jetty.stop();
	}

	@Test
	public void testEmbedJettyIntStringStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddServlet() {
		fail("Not yet implemented");
	}

	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testStop() {
		fail("Not yet implemented");
	}

}

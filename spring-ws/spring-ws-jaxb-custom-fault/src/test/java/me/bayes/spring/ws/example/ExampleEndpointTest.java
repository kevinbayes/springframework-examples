
/**
 * Copyright 2013 Bayes Technologies
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.bayes.spring.ws.example;

import static org.junit.Assert.*;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/webservices.xml"})
public class ExampleEndpointTest {
	
	private static final String REQUEST = "<Q1:ExampleRequest xmlns:Q1=\"http://schemas.bayes.me/example\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://schemas.bayes.me/example Example-1.0.xsd \">" + 
			"<Q1:SomeInput>Q1:SomeInput</Q1:SomeInput>" + 
			"</Q1:ExampleRequest>";
	private static final String FAULT = "<SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><faultcode>SOAP-ENV:Client</faultcode><faultstring xml:lang=\"en\">Some message from the example exception</faultstring><detail><ns2:ExampleFault xmlns:ns2=\"http://schemas.bayes.me/example\"><ns2:Code>UNKNOWN</ns2:Code><ns2:Message>Random Error</ns2:Message></ns2:ExampleFault></detail></SOAP-ENV:Fault>";
	
	@Autowired
	protected ApplicationContext applicationContext;
	
	protected MockWebServiceClient mockClient;
	
	@Before
	public void before() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}

	@Test
	public void simpleRequestAndExpectedTest() {
		//GIVEN
		Source requestEnvelope = new StringSource(REQUEST);
		Source responseEnvelope = new StringSource(FAULT);

		mockClient.sendRequest(withPayload(requestEnvelope)) // WHEN
				.andExpect(payload(responseEnvelope)); // THEN
	}

}

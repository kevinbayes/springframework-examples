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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;

import me.bayes.schemas.example.FaultType;
import me.bayes.schemas.example.ObjectFactory;

import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

/**
 * <p>
 * Now this is just an example you could just use the standard annotations to do a spring fault
 * but this is more flexible.
 * </p>
 * 
 * @author Kevin Bayes
 */
public class JaxbCustomSoapFaultMappingExceptionResolver extends SoapFaultMappingExceptionResolver {

	private ObjectFactory factory = new ObjectFactory();
	
	private JAXBContext context;
		
		public JaxbCustomSoapFaultMappingExceptionResolver() throws JAXBException {
			super();
			context = JAXBContext.newInstance("me.bayes.schemas.example");
		}
		
		@Override
		protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
			if(ex instanceof ExampleException) {
				try {
					addFaultDetail(endpoint, (ExampleException)ex, fault);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void addFaultDetail(Object endpoint, ExampleException exception, SoapFault fault) throws JAXBException { 
			
			final SoapFaultDetail detail = fault.addFaultDetail();
			final Marshaller marshaller = context.createMarshaller();
			final Result result = detail.getResult();
			 
			final FaultType type = factory.createFaultType();
			type.setCode(exception.getCode().name());
			type.setMessage(exception.getCause().getMessage());
			
			marshaller.marshal(factory.createExampleFault(type), result);
		} 

	

}

/**
 * Copyright 2014 Bayes Technologies
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

import me.bayes.schemas.example.ExampleRequest;
import me.bayes.schemas.example.ExampleResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Really silly example of an endpoint.
 * 
 * @author Kevin Bayes
 */
@Endpoint
public class ExampleEndpoint {

	@PayloadRoot(namespace = "http://schemas.bayes.me/example", localPart = "ExampleRequest")
	@ResponsePayload
	public ExampleResponse method(@RequestPayload ExampleRequest request) {
		throw new ExampleException(ExceptionCode.UNKNOWN, new Exception("Random Error"));
	}

}

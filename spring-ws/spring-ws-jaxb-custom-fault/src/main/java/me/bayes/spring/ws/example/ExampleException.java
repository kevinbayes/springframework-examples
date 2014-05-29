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

/**
 * <p>
 * This class will just hold the Exception code and be cause by our exception resolver which 
 * will then create the custom fault using this exception.
 * </p>
 * 
 * @author Kevin Bayes
 * @since 1.0.0
 */
public class ExampleException extends RuntimeException {

	private static final long serialVersionUID = 5211681180977879034L;
	
	private ExceptionCode code; //To hold something random
	
	public ExampleException(ExceptionCode code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public ExceptionCode getCode() {
		return code;
	}
	
	@Override
	public String getMessage() {
		return "Some message from the example exception";
	}
	
}

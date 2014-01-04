package com.carl.demo;

import javax.ejb.Remote;

@Remote
public interface Greeter {
	public String greet(String message);
}

package com.carl.demo;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class Demo
 */
@Stateless
public class Demo implements DemoRemote {

	/**
	 * Default constructor.
	 */
	public Demo() {
	}

	public Integer add(int i, int j) {
		return 1+1;
	}


}

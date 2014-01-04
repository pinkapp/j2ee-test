package com.carl.demo;

import javax.ejb.Remote;

@Remote
public interface DemoRemote {
	public Integer add(int i, int j);
}

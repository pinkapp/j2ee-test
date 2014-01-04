package com.carl.demo;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface UserManagerRemote {
	public void save();
	public List<User> findAll();
}

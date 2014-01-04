package com.handou.ejbclient;

import javax.naming.InitialContext;

import com.carl.demo.UserManagerRemote;

public class EjbClient {

	public static void main(String[] agrs) throws Exception {

		InitialContext ict = new InitialContext();
		UserManagerRemote remote = (UserManagerRemote) ict.lookup("userManager");
		for (int i = 0; i < 10; i++){
			remote.save();
		}
		System.out.println(remote.findAll());

	}
}

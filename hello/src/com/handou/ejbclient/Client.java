package com.handou.ejbclient;

import java.util.Date;
import java.util.UUID;

import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.naming.InitialContext;

import com.carl.demo.User;

public class Client {
	public static void main(String[] args) throws Exception {
		InitialContext ctx = new InitialContext();
		QueueConnection connection = null;
		QueueSession session = null;
		QueueConnectionFactory factory = (QueueConnectionFactory) ctx
				.lookup("ConnectionFactory");
		connection = factory.createQueueConnection();
		session = connection.createQueueSession(false,
				QueueSession.AUTO_ACKNOWLEDGE);
		Destination destination = (Queue) ctx.lookup("queue/MDBQueue");
		MessageProducer messageProducer = session.createProducer(destination);
		ObjectMessage objectMessage = session.createObjectMessage();
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setId(UUID.randomUUID().toString());
			objectMessage.setObject(user);
			messageProducer.send(objectMessage);
		}
		connection.close();
		System.out.println("成功发送消息！");
	}
}
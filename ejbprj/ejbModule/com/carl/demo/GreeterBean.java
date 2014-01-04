package com.carl.demo;

import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

@Stateless
public class GreeterBean implements Greeter {
	@Resource(mappedName = "ConnectionFactory")
	private ConnectionFactory cf;
	@Resource(mappedName = "queue/MDBQueue")
	private Queue queue;

	public String greet(String message) {
		try {
			User db = new User();
			db.setId(UUID.randomUUID().toString());
			Connection connection = cf.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			ObjectMessage objectMessage = session.createObjectMessage();
			objectMessage.setObject(db);
			messageProducer.send(objectMessage);
			connection.close();
			System.out.println("成功发送消息！");
		} catch (Exception e) {
			System.out.println("发送消息失败！");
		}
		return "方法成功返回";
	}
}
package com.carl.demo;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Message-Driven Bean implementation class for: MyMessageDrivenBean
 * 
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/MDBQueue") })
public class MyMessageDrivenBean implements MessageListener {
	@PersistenceContext(unitName = "jpaPU")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public MyMessageDrivenBean() {

	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objmsg = (ObjectMessage) message;
				User user = (User) objmsg.getObject();
				entityManager.persist(user);
				System.out.println("成功持久化DateBean对象!");
			} else {
				System.out.println("消息类型错误！");
			}
		} catch (Exception e) {
			throw new EJBException(e);
		}

	}
}

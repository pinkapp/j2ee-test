package com.carl.demo;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class Test
 */
@Stateless(description="用户管理bean",mappedName="userManager")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserManager implements UserManagerRemote {

	@PersistenceContext(unitName = "jpaPU")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UserManager() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save() {
		User u = new User();
		u.setId(UUID.randomUUID().toString());
		entityManager.persist(u);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return entityManager.createNamedQuery("findAll").getResultList();
	}

}

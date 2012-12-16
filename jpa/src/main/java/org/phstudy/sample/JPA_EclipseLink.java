package org.phstudy.sample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.phstudy.entity.Travel;

public class JPA_EclipseLink {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EclipseLink_SQLServer");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Travel t = new Travel();
		t.setFromWhere("TWJUG");
		em.persist(t);
		
		em.createQuery("SELECT t FROM Travel t", Travel.class).setFirstResult(3).setMaxResults(10).getResultList();

		em.getTransaction().commit();
		em.close();
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author HP
 */
@Stateless
public class validate {

    @PersistenceContext(unitName = "jpapu")
    EntityManager em;
    
    public boolean isEmailExists(String Email) {
        System.out.println(Email);

        TypedQuery<Long> query = em.createQuery("SELECT COUNT(r) FROM RegisterMstr r WHERE r.email = :Email", Long.class);
        query.setParameter("Email", Email);
        
        
        return query.getSingleResult() > 0;
    }
}

/**
 * 
 */
package com.homework.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.homework.entities.Actor;

/**
 * @author asd
 *
 */
@Repository("adminDAO")
public class AdminDAOImp implements AdminDAO {
    private SessionFactory sessionFactory;
    
    @Autowired(required=true)
    public AdminDAOImp(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.AdminDAO#getClients()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Actor> getClients() {
	Session s = this.sessionFactory.openSession();
	List<Actor> users = s.createQuery("from Actor").list();
	s.close();
	return users;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.AdminDAO#releaseBill(com.homework.entities.Bill)
     */
    @Override
    public void releaseBill(int billId) {
	String hql = "UPDATE Bill set isBlocked = false "  + 
                "WHERE id = :billId";
	Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("billId", billId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }
}

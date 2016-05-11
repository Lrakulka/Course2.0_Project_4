/**
 * 
 */
package com.homework.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homework.entities.Bill;
import com.homework.entities.User;

/**
 * @author asd
 *
 */
public class AdminDAOImp implements AdminDAO {
    private SessionFactory sessionFactory;
    
    public AdminDAOImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.AdminDAO#getClients()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> getClients() {
	Session s = this.sessionFactory.openSession();
	List<User> users = s.createQuery("from User").list();
	s.close();
	return users;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.AdminDAO#releaseBill(com.homework.entities.Bill)
     */
    @Override
    public void releaseBill(Bill bill) {
	Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        bill.setIsBlocked(false);
        session.persist(bill);
        tx.commit();
        session.close();
    }
}

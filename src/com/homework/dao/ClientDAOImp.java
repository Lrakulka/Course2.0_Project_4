/**
 * 
 */
package com.homework.dao;

import java.util.List;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homework.entities.Card;
import com.homework.entities.User;

/**
 * @author asd
 *
 */
public class ClientDAOImp implements ClientDAO {
    private SessionFactory sessionFactory;
    
    public ClientDAOImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#getCards(com.homework.entities.User)
     */
    // TODO
    @Override
    public List<Card> getCards(User user) {
	Session s = this.sessionFactory.openSession();
	List<User> users = s.createQuery("from Card C where C.user = ").list();
	s.close();
	return null;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#blockCard(com.homework.entities.Card)
     */
    @Override
    public void blockCard(Card card) {
	Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        card.getBill().setIsBlocked(true);
        session.persist(card.getBill());
        tx.commit();
        session.close();
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#makePayment(com.homework.entities.Card, double)
     */
    @Override
    public void makePayment(Card card, double payment) {
	if (!card.getBill().getIsBlocked()) {
    	    Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            card.getBill().setScore(card.getBill().getScore() - payment);
            session.persist(card.getBill());
            tx.commit();
            session.close();	
	}
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#fillCard(com.homework.entities.Card, double)
     */
    @Override
    public void fillCard(Card card, double fill) {
	if (!card.getBill().getIsBlocked()) {
    	    Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            card.getBill().setScore(card.getBill().getScore() + fill);
            session.persist(card.getBill());
            tx.commit();
            session.close();	
	}
    }
}

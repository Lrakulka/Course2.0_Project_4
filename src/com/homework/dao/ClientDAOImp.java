/**
 * 
 */
package com.homework.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.Query;
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
     * @see com.homework.dao.ClientDAO#getUser(com.homework.entities.User)
     */
    @SuppressWarnings("unchecked")
    @Override
    public User getUser(String name, String pass) {
	Session s = this.sessionFactory.openSession();
	Query query = s.createQuery("FROM User U WHERE U.name=:name AND U.pass=:pass");
	query.setParameter("name", name);
	query.setParameter("pass", pass);
	List<User> users = query.list();
	s.close();
	if (users.size() > 1 || users.isEmpty()) {
	    return null;
	} else {
	    return users.get(0);
	}
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#blockCard(com.homework.entities.Card)
     */
    @Override
    public Card blockCard(User user, int cardId) {
	Card card = check(user, cardId);
	if (card != null) {
    	    Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            card.getBill().setIsBlocked(true);
            session.persist(card.getBill());
            tx.commit();
            session.close();
	}
        return card;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#makePayment(com.homework.entities.Card, double)
     */
    @Override
    public Card makePayment(User user, int cardId, double payment) {
	Card card = check(user, cardId);
	if (card != null) {
	    if (!card.getBill().getIsBlocked()) {
        	Session session = this.sessionFactory.openSession();
                Transaction tx = session.beginTransaction();
                card.getBill().setScore(card.getBill().getScore() - payment);
                session.persist(card.getBill());
                tx.commit();
                session.close();	
	    }
	}
	return card;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#fillCard(com.homework.entities.Card, double)
     */
    @Override
    public Card fillCard(User user, int cardId, double fill) {
	Card card = check(user, cardId);
	if (card != null) {
	    if (!card.getBill().getIsBlocked()) {
        	Session session = this.sessionFactory.openSession();
                Transaction tx = session.beginTransaction();
                card.getBill().setScore(card.getBill().getScore() + fill);
                session.persist(card.getBill());
                tx.commit();
                session.close();	
	    }
	}
	return card;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#check(com.homework.entities.User, int)
     */
    @Override
    public Card check(User user, int cardId) {
	Card card = null;
	try {
	    card = user.getCards().stream().filter(c -> {
	            		return c.getId().equals(cardId);
	    		}).findFirst().get();
	} catch (NoSuchElementException e) {
	    e.printStackTrace();
	}
	return card;
    }
}

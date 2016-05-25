/**
 * 
 */
package com.homework.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homework.entities.Card;
import com.homework.entities.Actor;

/**
 * @author asd
 * Realization of actor DAO interface
 */
@Repository("clientDAO")
public class ClientDAOImp implements ClientDAO {
    private static final Logger logger = Logger.getLogger(ClientDAOImp.class);
    private SessionFactory sessionFactory;
    
    @Autowired(required=true)
    public ClientDAOImp(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#getUser(com.homework.entities.User)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Actor getUser(String name, String pass) {
	logger.info(new StringBuilder("Entering name=").
		append(name.charAt(0)).append("*** pass=***"));
	Session s = this.sessionFactory.openSession();
	Query query = s.createQuery("FROM Actor A WHERE A.name=:name AND A.pass=:pass");
	query.setParameter("name", name);
	query.setParameter("pass", pass);
	List<Actor> users = query.list();
	s.close();
	logger.info(new StringBuilder("Leaving usersCount=").
		append(users.size()));
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
    public Card blockCard(Actor user, int cardId) {
	logger.info(new StringBuilder("Entering name=").
		append(user.getName().charAt(0)).append("*** cardId=" + cardId));
	Card card = check(user, cardId);
	if (card != null) {
    	    Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            card.getBill().setIsBlocked(true);
            session.update(card.getBill());
            tx.commit();
            session.close();
	}
	if (card == null) {
	    logger.info("Leaving card=null");
	} else {
	    logger.info(new StringBuilder("Leaving card=").
		append(card.getName().charAt(0)).append("***"));
	}
        return card;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#makePayment(com.homework.entities.Card, double)
     */
    @Override
    public Card makePayment(Actor user, int cardId, double payment) {
	logger.info(new StringBuilder("Entering name=").
		append(user.getName().charAt(0)).
		append("*** cardId=" + cardId).append(" payment=").
		append(payment));
	Card card = check(user, cardId);
	if (card != null) {
	    if (!card.getBill().getIsBlocked()) {
        	Session session = this.sessionFactory.openSession();
                Transaction tx = session.beginTransaction();
                card.getBill().setScore(card.getBill().getScore() - payment);
                session.update(card.getBill());
                tx.commit();
                session.close();	
	    }
	}
	if (card == null) {
	    logger.info("Leaving card=null");
	} else {
	    logger.info(new StringBuilder("Leaving card=").
		append(card.getName().charAt(0)).append("***"));
	}
	return card;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#fillCard(com.homework.entities.Card, double)
     */
    @Override
    public Card fillCard(Actor user, int cardId, double fill) {
	logger.info(new StringBuilder("Entering name=").
		append(user.getName().charAt(0)).
		append("*** cardId=" + cardId).append(" fill=").
		append(fill));
	Card card = check(user, cardId);
	if (card != null) {
	    if (!card.getBill().getIsBlocked()) {
        	Session session = this.sessionFactory.openSession();
                Transaction tx = session.beginTransaction();
                card.getBill().setScore(card.getBill().getScore() + fill);
                session.update(card.getBill());
                tx.commit();
                session.close();	
	    }
	}
	if (card == null) {
	    logger.info("Leaving card=null");
	} else {
	    logger.info(new StringBuilder("Leaving card=").
		append(card.getName().charAt(0)).append("***"));
	}
	return card;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#check(com.homework.entities.User, int)
     */
    @Override
    public Card check(Actor user, int cardId) {
	logger.info(new StringBuilder("Entering name=").
		append(user.getName().charAt(0)).
		append("*** cardId=" + cardId));
	Card card = null;
	try {
	    card = user.getCards().stream().filter(c -> {
	            		return c.getId().equals(cardId);
	    		}).findFirst().get();
	} catch (NoSuchElementException e) {
	    e.printStackTrace();
	}
	if (card == null) {
	    logger.info("Leaving card=null");
	} else {
	    logger.info(new StringBuilder("Leaving card=").
		append(card.getName().charAt(0)).append("***"));
	}
	return card;
    }
}

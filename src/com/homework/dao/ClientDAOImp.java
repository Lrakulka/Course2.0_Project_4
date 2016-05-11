/**
 * 
 */
package com.homework.dao;

import java.util.Set;

import org.hibernate.SessionFactory;

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
    @Override
    public Set<Card> getCards(User user) {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#blockCard(com.homework.entities.Card)
     */
    @Override
    public void blockCard(Card card) {
	// TODO Auto-generated method stub
	
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#makePayment(com.homework.entities.Card, double)
     */
    @Override
    public void makePayment(Card card, double payment) {
	// TODO Auto-generated method stub
	
    }

    /* (non-Javadoc)
     * @see com.homework.dao.ClientDAO#fillCard(com.homework.entities.Card, double)
     */
    @Override
    public void fillCard(Card card, double fill) {
	// TODO Auto-generated method stub
	
    }
}

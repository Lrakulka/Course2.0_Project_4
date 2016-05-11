/**
 * 
 */
package com.homework.dao;

import java.util.Set;

import org.hibernate.SessionFactory;

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
    @Override
    public Set<User> getClients() {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see com.homework.dao.AdminDAO#releaseBill(com.homework.entities.Bill)
     */
    @Override
    public void releaseBill(Bill bill) {
	// TODO Auto-generated method stub
	
    }
}

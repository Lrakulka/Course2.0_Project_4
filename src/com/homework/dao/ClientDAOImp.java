/**
 * 
 */
package com.homework.dao;

import org.hibernate.SessionFactory;

/**
 * @author asd
 *
 */
public class ClientDAOImp implements ClientDAO {
    private SessionFactory sessionFactory;
    
    public ClientDAOImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

/**
 * 
 */
package com.homework.dao;

import org.hibernate.SessionFactory;

/**
 * @author asd
 *
 */
public class AdminDAOImp implements AdminDAO {
    private SessionFactory sessionFactory;
    
    public AdminDAOImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

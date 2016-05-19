package com.homework.dao;
import java.util.ArrayList;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homework.entities.Actor;

@Repository("actorDAO")
public class ActorDaoImpl implements ActorDAO {
    private SessionFactory sessionFactory;

    @Autowired(required=true)
    public ActorDaoImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @SuppressWarnings("unchecked")
    public Actor findByActorName(String actorName) {
	List<Actor> users = new ArrayList<Actor>();
	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	users = session
		.createQuery("from Actor where act_name=?")
		.setParameter(0, actorName).list();
	tx.commit();
        session.close();
	if (users.size() > 0) {
	    return users.get(0);
	} else {
	    return null;
	}
    }
}
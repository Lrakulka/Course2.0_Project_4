package com.homework.dao;
import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.homework.entities.Actor;

@Repository("actorDAO")
public class ActorDaoImpl implements ActorDAO {
    private static final Logger logger = Logger.getLogger(ActorDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired(required=true)
    public ActorDaoImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @SuppressWarnings("unchecked")
    public Actor findByActorName(String actorName) {
	logger.info(new StringBuilder("Entering actorName=").append(actorName));
	List<Actor> users = new ArrayList<Actor>();
	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	users = session.createQuery("from Actor where name=?")
		.setParameter(0, actorName).list();
	tx.commit();
        session.close();        
	if (users.size() > 0) {
	    logger.info(new StringBuilder("Leaving usersCount=").append(users.size()).
		    		append(" user(0)name=").
		    		append(users.get(0).getName().charAt(0)).
		    		append("***"));
	    return users.get(0);
	} else {
	    return null;
	}
    }
}
package com.homework.dao;

import com.homework.entities.Actor;

/**
 * 
 * @author asd
 * Interface of actor DAO
 */
public interface ActorDAO {
    /**
     * Finds actor in DB by it name
     * @param username - name of looking actor
     * @return actor object or null if such actor not found.
     */
    Actor findByActorName(String username);
}
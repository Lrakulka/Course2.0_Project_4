/**
 * 
 */
package com.homework.dao;

import com.homework.entities.Actor;

import com.homework.entities.Card;
/**
 * @author asd
 * Interface of client DAO
 */
public interface ClientDAO {
    /**
     * Get user by name and password.
     * @param name - user name
     * @param pass - user password
     * @return - user or null if no such user
     */
    Actor getUser(String name, String pass);
    
    /**
     * Block bill which connected to this card.
     * @param card which connected to the bill
     */
    Card blockCard(Actor user, int cardId);
    
    /**
     * Make payment with bill which connected to the card
     * @param card which connected to the bill
     */
    Card makePayment(Actor user, int cardId, double payment);
    
    /**
     * Fill bill which connected to the card
     * @param card which connected to the bill
     */
    Card fillCard(Actor user, int cardId, double fill);
    
    /**
     * Check if user is owner of card.
     * @param user - user of card
     * @param cardId - id of user card
     * @return true if user is owner of card else return false
     */
    Card check(Actor user, int cardId);
}

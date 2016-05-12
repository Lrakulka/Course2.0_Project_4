/**
 * 
 */
package com.homework.dao;

import com.homework.entities.User;

import com.homework.entities.Card;
/**
 * @author asd
 * DAO interface for actor client.
 */
public interface ClientDAO {
    /**
     * Get user by name and password.
     * @param name - user name
     * @param pass - user name
     * @return - user or null if no such user
     */
    User getUser(String name, String pass);
    
    /**
     * Block bill which connected to this card.
     * @param card which connected to the bill
     */
    Card blockCard(User user, int cardId);
    
    /**
     * Make payment with bill which connected to the card
     * @param card which connected to the bill
     */
    Card makePayment(User user, int cardId, double payment);
    
    /**
     * Fill bill which connected to the card
     * @param card which connected to the bill
     */
    Card fillCard(User user, int cardId, double fill);
    
    /**
     * Check if user is owner of card.
     * @param user - user of card
     * @param cardId - id of user card
     * @return true if user is owner of card else return false
     */
    Card check(User user, int cardId);
}

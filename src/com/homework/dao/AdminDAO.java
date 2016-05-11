/**
 * 
 */
package com.homework.dao;

import java.util.Set;

import com.homework.entities.Bill;
import com.homework.entities.User;

/**
 * @author asd
 *
 */
public interface AdminDAO {
    /**
     * Return set of all clients.
     * @return set of clients
     */
    Set<User> getClients();
    
    /**
     * Take block off of client bill.
     * @param bill
     */
    void releaseBill(Bill bill);
}

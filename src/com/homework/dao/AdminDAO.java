/**
 * 
 */
package com.homework.dao;

import java.util.List;

import com.homework.entities.Bill;
import com.homework.entities.User;

/**
 * @author asd
 * DAO interface for actor admin.
 */
public interface AdminDAO {
    /**
     * Return set of all clients.
     * @return set of clients
     */
    List<User> getClients();
    
    /**
     * Take block off of client bill.
     * @param bill
     */
    void releaseBill(Bill bill);
}

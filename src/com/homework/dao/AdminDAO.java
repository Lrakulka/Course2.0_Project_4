/**
 * 
 */
package com.homework.dao;

import java.util.List;
import com.homework.entities.Actor;

/**
 * @author asd
 * Interface of admin DAO.
 */
public interface AdminDAO {
    /**
     * Return list of all clients.
     * @return set of clients
     */
    List<Actor> getClients();
    
    /**
     * Take block off of client bill.
     * @param bill
     */
    void releaseBill(int billId);
}

/**
 * 
 */
package com.homework.dao;

import java.util.List;
import com.homework.entities.Actor;

/**
 * @author asd
 * DAO interface for actor admin.
 */
public interface AdminDAO {
    /**
     * Return set of all clients.
     * @return set of clients
     */
    List<Actor> getClients();
    
    /**
     * Take block off of client bill.
     * @param bill
     */
    void releaseBill(int billId);
}

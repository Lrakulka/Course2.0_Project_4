/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.homework.dao.AdminDAO;
import com.homework.dao.AdminDAOImp;
import com.homework.dao.ClientDAO;
import com.homework.dao.ClientDAOImp;
import com.homework.entities.Bill;
import com.homework.entities.Card;
import com.homework.entities.Actor;
/**
 * @author asd
 *
 */
public class JUnitTestAdminDAO {
    private static AdminDAO adminDAO;
    private static ClassPathXmlApplicationContext context;
    private List<Actor> clients;
    private List<Actor> admins;
    
    {
	admins = new ArrayList<Actor>() {
	    private static final long serialVersionUID = 7037590408585006818L;

	    {
		Actor user = new Actor("user1", "1223");
		Card card = new Card("card1");
		Bill bill = new Bill(false, 400);
		user.setCards(new HashSet<Card>() {
		    { add(card);}
		});
		card.setBill(bill);
		add(user);
	    }
	};
    }
    
    @BeforeClass
    public static void initTests() {
	context = new ClassPathXmlApplicationContext("resources/springTest.xml");
        adminDAO = context.getBean(AdminDAOImp.class);
    }
    
    @AfterClass
    public static void closeTests() {
        context.close();    
    }
 
    @Before
    public void initDB() {
	
    }
    @After
    public void cleanDB() {
	
    }
    
    @Test
    public void getAllUsers() {
	List<Actor> clients = adminDAO.getClients();
	clients.stream().forEach(System.out::println);
	clients.get(0).getCards().stream().forEach(System.out::println);
	assertTrue(true);
    }

}

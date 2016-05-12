/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.homework.dao.AdminDAO;
import com.homework.dao.AdminDAOImp;
import com.homework.dao.ClientDAO;
import com.homework.dao.ClientDAOImp;
import com.homework.entities.User;
/**
 * @author asd
 *
 */
public class JUnitTests {
    private static AdminDAO adminDAO;
    private static ClientDAO clientDAO;
    private static ClassPathXmlApplicationContext context;
    
    @BeforeClass
    public static void initTests() {
	context = new ClassPathXmlApplicationContext("spring.xml");
        adminDAO = context.getBean(AdminDAOImp.class);
        clientDAO = context.getBean(ClientDAOImp.class);
    }
    
    @AfterClass
    public static void destroy() {
        context.close();    
    }
 
    @Test
    public void getAllUsers() {
	adminDAO.getClients().stream().forEach(System.out::println);
	assertTrue(true);
    }

}

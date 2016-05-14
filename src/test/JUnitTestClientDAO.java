/**
 * 
 */
package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.homework.dao.AdminDAOImp;
import com.homework.dao.ClientDAO;
import com.homework.dao.ClientDAOImp;

/**
 * @author asd
 *
 */
public class JUnitTestClientDAO {
    private static ClientDAO clientDAO;
    private static ClassPathXmlApplicationContext context;
    
    @BeforeClass
    public static void initTests() {
	context = new ClassPathXmlApplicationContext("resources/springTest.xml");
        clientDAO = context.getBean(ClientDAOImp.class);
    }
    
    @AfterClass
    public static void destroy() {
        context.close();    
    }
    
    
}

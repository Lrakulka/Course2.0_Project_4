/**
 * 
 */
package test;

import java.util.List;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.homework.dao.AdminDAO;
import com.homework.entities.User;
/**
 * @author asd
 *
 */
public class Test {
    public static void main(String... args) {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	
        AdminDAO adminDAO = context.getBean(AdminDAO.class);
       
        List<User> list = adminDAO.getClients();
       
        list.stream().forEach((u) -> System.out.println(u.toString()));
        list.get(0).getCards().stream().forEach(System.out::println);
        //close resources
        context.close();    
    }
}

/**
 * 
 */
package com.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.homework.dao.AdminDAO;
import com.homework.dao.ClientDAO;
import com.homework.entities.Actor;

/**
 * @author asd
 *
 */
@Controller
public class SpringControllerMVC {
    private AdminDAO adminDAO;
    private ClientDAO clientDAO;
    
    @Autowired(required=true)
    public void setAdminDAO(AdminDAO adminDAO, ClientDAO clientDAO) {
	this.adminDAO = adminDAO;
	this.clientDAO = clientDAO;
    }
    
    @RequestMapping(value = "/**")
    public ModelAndView mainPage() {
	ModelAndView model = new ModelAndView();
	model.setViewName("HelloWorld");
	model.addObject("actor", adminDAO.getClients().get(0).toString());
	
	return model;
    }
    
    @RequestMapping(value = "/admin")
    public ModelAndView adminHomePage() {
	ModelAndView model = new ModelAndView();
	model.setViewName("adminHomePage");
	model.addObject("clients", adminDAO.getClients());	
	return model;
    }
    
    @RequestMapping(value = "/client")
    public ModelAndView clientHomePage() {
	Actor user = adminDAO.getClients().get(0);
	ModelAndView model = new ModelAndView();
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	return model;
    }
}

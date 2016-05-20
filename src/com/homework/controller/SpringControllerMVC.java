/**
 * 
 */
package com.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView welcome() {
	ModelAndView model = new ModelAndView();
	model.setViewName("welcome");	
	
	return model;
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHomePage() {
	ModelAndView model = new ModelAndView();
	model.setViewName("adminHomePage");
	model.addObject("clients", adminDAO.getClients());	
	
	return model;
    }
    
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ModelAndView clientHomePage() {
	Actor user = adminDAO.getClients().get(0);
	ModelAndView model = new ModelAndView();
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	return model;
    }
    
    @RequestMapping(value = "/releaseClientBill", method = RequestMethod.POST)
    public ModelAndView releaseClientBill(@RequestParam("billBlockId") int billId) {
	adminDAO.releaseBill(billId);
	
	ModelAndView model = new ModelAndView();
	model.setViewName("adminHomePage");
	model.addObject("clients", adminDAO.getClients());
	return model;
    }
    
    // Card table handlers
    @RequestMapping(value = "/blockClientBill", method = RequestMethod.POST)
    public ModelAndView blockClientBill(@RequestParam("blockBill") int cardId) {

	Actor user = adminDAO.getClients().get(0);
	
	clientDAO.blockCard(user, cardId);
	ModelAndView model = new ModelAndView();
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	return model;
    }
    
    @RequestMapping(value = "/fillClientBill", method = RequestMethod.POST)
    public ModelAndView fillClientBill(@RequestParam("fillBill") int cardId, 
	    @RequestParam("moneyCount") double money) {

	Actor user = adminDAO.getClients().get(0);
	
	clientDAO.fillCard(user, cardId, money);
	ModelAndView model = new ModelAndView();
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	return model;
    }
    
    @RequestMapping(value = "/makeClientPayment", method = RequestMethod.POST)
    public ModelAndView makeClientPayment(@RequestParam("makePayment") int cardId, 
	    @RequestParam("moneyCount") double payment) {

	Actor user = adminDAO.getClients().get(0);
	
	clientDAO.makePayment(user, cardId, payment);
	ModelAndView model = new ModelAndView();
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	return model;
    }
}

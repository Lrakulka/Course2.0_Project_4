/**
 * 
 */
package com.homework.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.homework.dao.ActorDAO;
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
    private ActorDAO actorDAO;
    
    @Autowired(required=true)
    public void setAdminDAO(AdminDAO adminDAO, ClientDAO clientDAO, 
	    ActorDAO actorDAO) {
	this.adminDAO = adminDAO;
	this.clientDAO = clientDAO;
	this.actorDAO = actorDAO;
    }
    
    @RequestMapping(value = "/**")
    public ModelAndView welcome() {
	ModelAndView model = new ModelAndView();
	model.setViewName("welcome");		
	return model;
    }
    
    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
  	@RequestParam(value = "error", required = false) String error,
  	@RequestParam(value = "logout", required = false) String logout) {
  	ModelAndView model = new ModelAndView();  	
  	if (error != null) {
  	    model.addObject("error", "Invalid username and password!");
  	}
  	if (logout != null) {
  	    model.addObject("msg", "You've been logged out successfully.");
  	}
  	model.setViewName("login");
  	return model;
    }
  
    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied(Principal principal) {
	ModelAndView model = new ModelAndView();
	// check if user is login
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (!(auth instanceof AnonymousAuthenticationToken)) {
	    model.addObject("username", principal.getName());
		model.setViewName("403");
	} else {
	    model.setViewName("welcome");
	}
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
    public ModelAndView clientHomePage(Principal principal) {
	Actor user = actorDAO.findByActorName(principal.getName());
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
    public ModelAndView blockClientBill(@RequestParam("blockBill") int cardId, 
	    Principal principal) {
	Actor user = actorDAO.findByActorName(principal.getName());	
	clientDAO.blockCard(user, cardId);
	ModelAndView model = new ModelAndView();
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	return model;
    }
    
    @RequestMapping(value = "/fillClientBill", method = RequestMethod.POST)
    public ModelAndView fillClientBill(@RequestParam("fillBill") int cardId, 
	    @RequestParam("moneyCount") double money, Principal principal) {
	Actor user = actorDAO.findByActorName(principal.getName());	
	clientDAO.fillCard(user, cardId, money);
	ModelAndView model = new ModelAndView();
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	return model;
    }
    
    @RequestMapping(value = "/makeClientPayment", method = RequestMethod.POST)
    public ModelAndView makeClientPayment(@RequestParam("makePayment") int cardId, 
	    @RequestParam("moneyCount") double payment, Principal principal) {
	Actor user = actorDAO.findByActorName(principal.getName());	
	clientDAO.makePayment(user, cardId, payment);
	ModelAndView model = new ModelAndView();
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	return model;
    }
}

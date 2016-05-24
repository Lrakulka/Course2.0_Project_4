/**
 * 
 */
package com.homework.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
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
    private static final Logger logger = Logger.getLogger(SpringControllerMVC.class);
    
    @Autowired(required=true)
    public void setAdminDAO(AdminDAO adminDAO, ClientDAO clientDAO, 
	    ActorDAO actorDAO) {
	this.adminDAO = adminDAO;
	this.clientDAO = clientDAO;
	this.actorDAO = actorDAO;
    }
    
    @RequestMapping(value = "/**")
    public ModelAndView welcome() {
	logger.info("Entering");
	ModelAndView model = new ModelAndView();
	model.setViewName("welcome");		
	logger.info(new StringBuilder("Leaving ").append(model.toString()));
	return model;
    }
    
    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
  	@RequestParam(value = "error", required = false) String error,
  	@RequestParam(value = "logout", required = false) String logout) {
	logger.info(new StringBuilder("Entering error=").append(error).
		append(" logout=").append(logout));
  	ModelAndView model = new ModelAndView();  	
  	if (error != null) {
  	    model.addObject("error", "Invalid username and password!");
  	}
  	if (logout != null) {
  	    model.addObject("msg", "You've been logged out successfully.");
  	}
  	model.setViewName("login");
  	logger.info(new StringBuilder("Leaving ").append(model.toString()));
  	return model;
    }
  
    //for 403 access denied page
    @RequestMapping(value = "/403**", method = RequestMethod.GET)
    public ModelAndView accesssDenied(Principal principal) {
	logger.info("Entering");
	ModelAndView model = new ModelAndView();
	// check if user is login
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (!(auth instanceof AnonymousAuthenticationToken)) {
	    model.addObject("username", principal.getName());
		model.setViewName("403");
	} else {
	    model.setViewName("welcome");
	}
	logger.info(new StringBuilder("Leaving ").append(model.toString()));
	return model;
    }
  	
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHomePage() {
	logger.info("Entering");
	ModelAndView model = new ModelAndView();
	model.setViewName("adminHomePage");
	model.addObject("clients", adminDAO.getClients());	
	logger.info(new StringBuilder("Leaving ").append(model.toString()));
	return model;
    }
    
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ModelAndView clientHomePage(Principal principal) {
	logger.info("Entering");
	Actor user = actorDAO.findByActorName(principal.getName());
	ModelAndView model = new ModelAndView();
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	logger.info(new StringBuilder("Leaving ").append(model.toString()));
	return model;
    }
    
    @RequestMapping(value = "/releaseClientBill", method = RequestMethod.POST)
    public ModelAndView releaseClientBill(@RequestParam("billBlockId") int billId) {
	logger.info(new StringBuilder("Entering billId=").append(billId));
	adminDAO.releaseBill(billId);	
	ModelAndView model = new ModelAndView();
	model.setViewName("adminHomePage");
	model.addObject("clients", adminDAO.getClients());
	logger.info(new StringBuilder("Leaving ").append(model.toString()));
	return model;
    }
    
    // Card table handlers
    @RequestMapping(value = "/blockClientBill", method = RequestMethod.POST)
    public ModelAndView blockClientBill(@RequestParam("blockBill") int cardId, 
	    Principal principal) {
	logger.info(new StringBuilder("Entering cardId=").append(cardId));
	Actor user = actorDAO.findByActorName(principal.getName());	
	clientDAO.blockCard(user, cardId);
	ModelAndView model = new ModelAndView();
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	logger.info(new StringBuilder("Leaving ").append(model.toString()));
	return model;
    }
    
    @RequestMapping(value = "/fillClientBill", method = RequestMethod.POST)
    public ModelAndView fillClientBill(@RequestParam("fillBill") int cardId, 
	    @RequestParam("moneyCount") String moneySt, Principal principal) {
	logger.info(new StringBuilder("Entering cardId=").append(cardId).
		append(" money=").append(moneySt).append(" principal=").
		append(principal));
	Actor user = actorDAO.findByActorName(principal.getName());
	ModelAndView model = new ModelAndView();
	try {
	    double money = Double.valueOf(moneySt);
	    clientDAO.fillCard(user, cardId, money);
	} catch (Exception e) {
	    logger.warn(new StringBuilder("Converting exception moneySt=").
		    		append(moneySt));
	    model.addObject("msg", "Converting error");
	}
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	logger.info(new StringBuilder("Leaving ").append(model.toString()));
	return model;
    }
    
    @RequestMapping(value = "/makeClientPayment", method = RequestMethod.POST)
    public ModelAndView makeClientPayment(@RequestParam("makePayment") int cardId, 
	    @RequestParam("moneyCount") String paymentSt, Principal principal) {
	logger.info(new StringBuilder("Entering cardId=").append(cardId).
		append(" paymentSt=").append(paymentSt).append(
		" principal.getName()=").append(principal.getName().
			charAt(0)).append("***").toString());
	Actor user = actorDAO.findByActorName(principal.getName());
	ModelAndView model = new ModelAndView();
	try {
	    double payment = Double.valueOf(paymentSt);
	    clientDAO.makePayment(user, cardId, payment);
	} catch (Exception e) {
	    model.addObject("msg", "Converting error");
	    logger.warn(new StringBuilder("Converting exception moneySt=").
		    		append(paymentSt));
	}
	model.setViewName("clientHomePage");
	model.addObject("cards", user.getCards());	
	logger.info(new StringBuilder("Leaving ").append(model.toString()));
	return model;
    }
}

/**
 * 
 */
package com.homework.tag;
import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import com.homework.entities.Actor;
import com.homework.entities.Card;

/**
 * @author asd
 *
 */
public class ClientsTable extends TagSupport {
    private static final long serialVersionUID = 8721266929256929800L;
    private String buttonInfo;
    private List<Actor> clients;
    
    public void setButtonInfo(String buttonInfo) {
	this.buttonInfo = buttonInfo;
    }
    
    public void setClients(List<Actor> actors) {
	this.clients = actors;
    }
    
    public int doStartTag() {
	StringBuilder tableBuilder = new StringBuilder(
		"<form name=\"ClientTable\" action=\"/Project_4/clientTable\" " +
			"method=\"post\">" +
			"<table border=\"2\" cellpadding=\"8\">");
	int i;
	try {
	    for (Actor actor : clients) {
		i = 0;
		if (actor.getCards().size() > 1) {
		    tableBuilder.append("<tr><td rowspan=" + 
			    actor.getCards().size() + ">" + 
			    actor.getName() + "</td>");
		} else {
		    tableBuilder.append("<tr><td>" + actor.getName() + 
			    "</td>");
		}
		for (Card card : actor.getCards()) {
		    if (i > 0) {
			tableBuilder.append("<tr>");
		    }
		    tableBuilder.append("<td>" + card.getName() +
			"</td><td>" + card.getBill().getScore() + 
			"</td><td>" + (card.getBill().getIsBlocked() ? "Blocked" :
			    "Not blocked") + "</td>");
		    if (card.getBill().getIsBlocked()) {
			tableBuilder.append("<td><button name=\"billBlockId\" value=\"" +
				card.getBill().getId() +
				"\">" + buttonInfo + "</button></td>");
		    }
		    i++;
		    if ((i > 0) && (i != actor.getCards().size())) {
			tableBuilder.append("</tr>");
		    }
		}
		tableBuilder.append("</tr>");
	    }
	    tableBuilder.append("</table></form>");
	    pageContext.getOut().write(tableBuilder.toString());
	} catch (Exception e) {
	   // LOGGER.error("Problem in lecture table(tag)", e);
	    
	}
	return SKIP_BODY;
    }
}

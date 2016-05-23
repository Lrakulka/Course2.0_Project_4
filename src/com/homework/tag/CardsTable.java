/**
 * 
 */
package com.homework.tag;

import java.util.List;
import javax.servlet.jsp.tagext.TagSupport;
import com.homework.entities.Card;

/**
 * @author asd
 *
 */
public class CardsTable extends TagSupport {
    private static final long serialVersionUID = -3905900903200282870L;
    private String buttonFillInfo;
    private String buttonMakePaymentInfo;
    private String buttonBlockInfo;
    private List<Card> cards;
    private String parameterName;
    private String token;
    private String textBlocked;
    private String textUnBlocked;
    
    public void setTextBlocked(String textBlocked) {
        this.textBlocked = textBlocked;
    }

    public void setTextUnBlocked(String textUnBlocked) {
        this.textUnBlocked = textUnBlocked;
    }
    
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public void setButtonFillInfo(String buttonFillInfo) {
        this.buttonFillInfo = buttonFillInfo;
    }

    public void setButtonMakePaymentInfo(String buttonMakePaymentInfo) {
        this.buttonMakePaymentInfo = buttonMakePaymentInfo;
    }

    public void setButtonBlockInfo(String buttonBlockInfo) {
        this.buttonBlockInfo = buttonBlockInfo;
    }

    public void setCards(List<Card> cards) {
	this.cards = cards;
    }
    
    public int doStartTag() {
	StringBuilder tableBuilder = new StringBuilder(
		"<table border=\"2\" cellpadding=\"8\">");
	try {
	    for (Card card : cards) {
		tableBuilder.append("<tr><td>" + card.getName() + 
			"</td><td>" +
			"<p align=\"center\">" + card.getBill().getScore() + "</p></td>" +
			"<td><form name=\"CardTable\" action=\"/Project_4/fillClientBill\" " +
			"method=\"post\">" +		
			"<input type=\"hidden\" name=\"" + parameterName +"\"" +
			"	value=\"" + token + "\" />" +
			"<p align=\"center\"><input size=\"10\" type=\"text\" name=\"moneyCount\" /></p>" +
			"<p align=\"center\"><button name=\"fillBill\" value=\"" + 
			card.getId() + "\">" + buttonFillInfo + 
			"</button></form></td>" + 
			"<td><form name=\"CardTable\" action=\"/Project_4/makeClientPayment\" " +
			"method=\"post\">" +	
			"<input type=\"hidden\" name=\"" + parameterName +"\"" +
			"	value=\"" + token + "\" />" +
			"<p align=\"center\"><input size=\"10\" type=\"text\" name=\"moneyCount\" /></p>" +
			"<p align=\"center\"><button name=\"makePayment\" value=\"" + 
			card.getId() + "\">" + buttonMakePaymentInfo + 
			"</button></form></td></p>" +
			"</td><td><p>" + (card.getBill().getIsBlocked() ? 
				textBlocked : textUnBlocked));
			if (!card.getBill().getIsBlocked()) {
			    tableBuilder.append("<form name=\"CardTable\" " +
			    		"action=\"/Project_4/blockClientBill\" " +
			    		"method=\"post\">" + 
			    		"<input type=\"hidden\" name=\"" + 
			    		parameterName +"\" value=\"" + token + "\" />" + 
			    		"</p><p align=\"center\">" +
					"<button name=\"blockBill\" value=\"" + 
					card.getId() + "\">" + buttonBlockInfo + 
					"</button></p></form></td>");	
			}
		tableBuilder.append("</tr>");
	    }
	    tableBuilder.append("</table>");
	    pageContext.getOut().write(tableBuilder.toString());
	} catch (Exception e) {
	   // LOGGER.error("Problem in lecture table(tag)", e);
	    
	}
	return SKIP_BODY;
    }
}

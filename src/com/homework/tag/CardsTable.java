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
		"<form name=\"CardTable\" action=\"/cardTable\" " +
			"method=\"post\">" +
			"<table border=\"2\" cellpadding=\"8\">");
	try {
	    for (Card card : cards) {
		tableBuilder.append("<tr><td>" + card.getName() + 
			"</td><td>" +
			"<p align=\"center\">" + card.getBill().getScore() + "</p>" +
			"<p><input type=\"hidden\" name=\"cardsId\" " +
			"value=\"" + card.getId() + "\" /></p>" +
			"<p><button name=\"fillBillId\" >" + buttonFillInfo + 
			"</button>" + "<button name=\"makePayment\" >" + 
			buttonMakePaymentInfo + "</button></p>" +
			"</td><td><p>" + (card.getBill().getIsBlocked() ? 
				"Blocked" : "Not blocked") + 
			"</p><p align=\"center\"><button name=\"blockBill\" value=\"" + 
			card.getId() + "\">" + buttonBlockInfo + "</button></p></td>");		
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

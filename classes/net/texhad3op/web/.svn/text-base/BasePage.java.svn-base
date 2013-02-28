package net.texhad3op.web;

import javax.ejb.EJB;

import net.texhad3op.ejb.CaliberLocal;
import net.texhad3op.ejb.CaliberOperationLocal;
import net.texhad3op.ejb.CaliberOrderLocal;
import net.texhad3op.ejb.WorkerLocal;

import org.apache.wicket.markup.html.WebPage;

public abstract class BasePage extends WebPage {

	@EJB(name = "WorkerEJB")
	public WorkerLocal workerLocal;

	@EJB(name = "CaliberOperationEJB")
	public CaliberOperationLocal caliberOperationLocal;

	@EJB(name = "CaliberEJB")
	public CaliberLocal caliberLocal;	
	
	@EJB(name = "caliberOrderEJB")
	public CaliberOrderLocal caliberOrderLocal;	
	
	public CaliberTrafficSession getCaliberTrafficSession() {
		return (CaliberTrafficSession) getSession();
	}

	public BasePage() {
		


	}
}

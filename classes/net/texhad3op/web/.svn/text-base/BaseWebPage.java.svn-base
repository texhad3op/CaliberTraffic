package net.texhad3op.web;

import net.texhad3op.ejb.Utils;
import net.texhad3op.ejb.entities.Worker;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.resources.StyleSheetReference;

public abstract class BaseWebPage extends BasePage {
	public BaseWebPage(){
        add(new StyleSheetReference("stylesheet", BasePage.class, "style.css"));
		if(null == getCaliberTrafficSession().getWorker()) setResponsePage(LoginPage.class);
		add(new Label("logged", getCaliberTrafficSession().getWorker().getFirstName()+" "+getCaliberTrafficSession().getWorker().getLastName()));
		add(new Link<Worker>("logout"){
			@Override
			public void onClick() {
				getCaliberTrafficSession().setWorker(null);
				setResponsePage(LoginPage.class);
			}
		});
		
		MenuComponent general2 = new MenuComponent("general", "Pagrindinis") {
			@Override
			public void onClick2() {
				setResponsePage(CalibersGeneralPage.class);
			}
		};
		add(general2);
		
		MenuComponent trafficByWorker = new MenuComponent("trafficByWorker", "Apyvarta pagal darbuotoją") {
			@Override
			public void onClick2() {
				setResponsePage(TrafficByWorkerSelectWorkerPage.class);
			}
			
			@Override
			public boolean isVisible(){
				return getCaliberTrafficSession().getWorker().getType().equals(Utils.getWorkerType(2));
			}			
		};
		add(trafficByWorker);		
		
		MenuComponent trafficByCaliber = new MenuComponent("trafficByCaliber", "Apyvarta pagal kalibrą") {
			@Override
			public void onClick2() {
				setResponsePage(TrafficByCaliberSelectCaliberPage.class);
			}
			
			@Override
			public boolean isVisible(){
				return getCaliberTrafficSession().getWorker().getType().equals(Utils.getWorkerType(2));
			}			
		};
		add(trafficByCaliber);			
		
		MenuComponent calibersChecking = new MenuComponent("calibersChecking", "Kalibrų patikrinimas") {
			@Override
			public void onClick2() {
				setResponsePage(CalibersCheckingSelectPage.class);
			}
			
			@Override
			public boolean isVisible(){
				return getCaliberTrafficSession().getWorker().getType().equals(Utils.getWorkerType(2));
			}			
		};
		add(calibersChecking);	
		
		MenuComponent workers = new MenuComponent("workers", "Darbuotojai") {
			@Override
			public void onClick2() {
				setResponsePage(WorkersPage.class);
			}
			
			@Override
			public boolean isVisible(){
				return getCaliberTrafficSession().getWorker().getType().equals(Utils.getWorkerType(2));
			}			
		};
		add(workers);	

		MenuComponent calibers = new MenuComponent("calibers", "Kalibrai") {
			@Override
			public void onClick2() {
				setResponsePage(CalibersPage.class);
			}
			
			@Override
			public boolean isVisible(){
				return getCaliberTrafficSession().getWorker().getType().equals(Utils.getWorkerType(2));
			}			
		};
		add(calibers);		
		
		MenuComponent orders = new MenuComponent("orders", "Užsakymai") {
			@Override
			public void onClick2() {
				setResponsePage(OrdersPage.class);
			}
			
			@Override
			public boolean isVisible(){
				return getCaliberTrafficSession().getWorker().getType().equals(Utils.getWorkerType(2));
			}			
		};
		add(orders);			
		
	}
}

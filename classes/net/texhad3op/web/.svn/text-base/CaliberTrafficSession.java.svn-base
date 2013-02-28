package net.texhad3op.web;

import net.texhad3op.ejb.entities.Caliber;
import net.texhad3op.ejb.entities.Worker;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class CaliberTrafficSession extends WebSession {
	
	private Worker worker;
	private Worker selectedWorker;
	private Caliber selectedCaliber;
	private String selectedMenuPunkt = "general";
	private Worker workerForEdit;
	private Boolean realCaliberEdit = false;
	
	protected CaliberTrafficSession(Request request) {
		super(request);
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public Worker getSelectedWorker() {
		return selectedWorker;
	}

	public void setSelectedWorker(Worker selectedWorker) {
		this.selectedWorker = selectedWorker;
	}

	public Caliber getSelectedCaliber() {
		return selectedCaliber;
	}

	public void setSelectedCaliber(Caliber selectedCaliber) {
		this.selectedCaliber = selectedCaliber;
	}

	public String getSelectedMenuPunkt() {
		return selectedMenuPunkt;
	}

	public void setSelectedMenuPunkt(String selectedMenuPunkt) {
		this.selectedMenuPunkt = selectedMenuPunkt;
	}

	public Worker getWorkerForEdit() {
		return workerForEdit;
	}

	public void setWorkerForEdit(Worker workerForEdit) {
		this.workerForEdit = workerForEdit;
	}

	public Boolean getRealCaliberEdit() {
		return realCaliberEdit;
	}

	public void setRealCaliberEdit(Boolean realCaliberEdit) {
		this.realCaliberEdit = realCaliberEdit;
	}
	
}

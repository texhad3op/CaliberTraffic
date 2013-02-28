package net.texhad3op.web;

import net.texhad3op.ejb.Utils;
import net.texhad3op.ejb.entities.Worker;
import net.texhad3op.ejb.entities.WorkerType;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class WorkerEditPage extends BasePresidentPermissedWebPage {

	public WorkerEditPage() {

		setDefaultModel(new Model<Worker>() {
			@Override
			public Worker getObject() {
				return getCaliberTrafficSession().getWorkerForEdit();
			}
		});

		final TextField<String> firstName = new TextField<String>("firstName", new PropertyModel<String>(getDefaultModel(), "firstName"));

		final TextField<String> lastName = new TextField<String>("lastName", new PropertyModel<String>(getDefaultModel(), "lastName"));

		final TextField<String> description = new TextField<String>("description", new PropertyModel<String>(getDefaultModel(),
				"description"));

		final TextField<String> userLogin = new TextField<String>("userLogin", new PropertyModel<String>(getDefaultModel(), "userLogin"));

		final TextField<String> userPassword = new TextField<String>("userPassword", new PropertyModel<String>(getDefaultModel(),
				"userPassword"));

		DropDownChoice<WorkerType> dropDownWorkerTypes = new DropDownChoice<WorkerType>("type",
				new PropertyModel(getDefaultModel(), "type"), Utils.workerTypes, new ChoiceRenderer("name", "id"));

		final CheckBox isBlocked = new CheckBox("isBlocked", new PropertyModel(getDefaultModel(), "isBlocked"));

		Form<Worker> form = new Form<Worker>("form") {
			@Override
			protected void onSubmit() {
				if (null == getCaliberTrafficSession().getWorkerForEdit().getId()) {
					Worker worker = (Worker) WorkerEditPage.this.getDefaultModel().getObject();
					workerLocal.create(worker);
					setResponsePage(WorkersPage.class);
				} else {
					Worker worker = (Worker) WorkerEditPage.this.getDefaultModel().getObject();
					workerLocal.edit(worker);
					setResponsePage(WorkersPage.class);
				}
			}
		};

		form.add(firstName);
		form.add(lastName);

		form.add(description);
		form.add(userLogin);
		form.add(userPassword);
		dropDownWorkerTypes.setRequired(true);
		form.add(dropDownWorkerTypes);
		form.add(isBlocked);

		add(form);

	}
}

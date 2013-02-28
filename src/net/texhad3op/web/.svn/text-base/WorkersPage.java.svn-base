package net.texhad3op.web;

import java.util.List;

import net.texhad3op.ejb.entities.Worker;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class WorkersPage extends BasePresidentPermissedWebPage {

	public WorkersPage() {
		final IModel<List<Worker>> modelWorkers = new LoadableDetachableModel<List<Worker>>() {
			@Override
			protected List<Worker> load() {
				return workerLocal.findAll() ;
			}
		};

		add(new ListView<Worker>("workersList", modelWorkers) {
			@Override
			protected void populateItem(ListItem<Worker> item) {
				Worker worker = item.getModelObject();
				
				item.add(new Label("firstName", worker.getFirstName()));			
				item.add(new Label("lastName", worker.getLastName()));				

				Link<Worker> link = new Link<Worker>("linkEdit", new Model<Worker>(worker)) {
					@Override
					public void onClick() {
						Worker w = getModelObject();
						getCaliberTrafficSession().setWorkerForEdit(workerLocal.find(w.getId()));
						setResponsePage(WorkerEditPage.class);
					}

				};
				item.add(link);

			}
		});

	}
}



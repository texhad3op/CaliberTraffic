package net.texhad3op.web;

import java.sql.Timestamp;
import java.util.List;

import net.texhad3op.ejb.Utils;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class CalibersByWorkerPage extends BasePresidentPermissedWebPage {

	public CalibersByWorkerPage() {
		final IModel<List<Object[]>> modelCalibersOperations = new LoadableDetachableModel<List<Object[]>>() {
			@Override
			protected List<Object[]> load() {
				return caliberOperationLocal.findAllOperationsByWorker(getCaliberTrafficSession().getSelectedWorker());
			}
		};

		add(new Label("selectedWorker", new Model<String>(getCaliberTrafficSession().getSelectedWorker().getFirstName() + " "
				+ getCaliberTrafficSession().getSelectedWorker().getLastName())));

		add(new ListView<Object[]>("operationsList", modelCalibersOperations) {
			@Override
			protected void populateItem(ListItem<Object[]> item) {
				Object[] tuple = item.getModelObject();
				Integer operationtype = (Integer) tuple[0];
				Timestamp registered = (Timestamp) tuple[1];
				String deviceNr = (String) tuple[2];

				item.add(new Label("operationtype", Utils.getOperationType(operationtype).getName()));
				item.add(new Label("registered", registered.toString()));

				item.add(new Label("deviceNr", deviceNr));
			}
		});

	}
}

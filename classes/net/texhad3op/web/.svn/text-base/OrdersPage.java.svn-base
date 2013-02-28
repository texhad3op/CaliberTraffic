package net.texhad3op.web;

import java.util.List;

import net.texhad3op.ejb.entities.CaliberOrder;
import net.texhad3op.ejb.entities.Worker;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class OrdersPage extends BasePresidentPermissedWebPage {

	public OrdersPage() {
		final IModel<List<CaliberOrder>> modelCaliberOrders = new LoadableDetachableModel<List<CaliberOrder>>() {
			@Override
			protected List<CaliberOrder> load() {
				return caliberOrderLocal.findActivesOrders();
			}
		};

		ListView<CaliberOrder> pv = new ListView<CaliberOrder>("caliberOrdersList", modelCaliberOrders) {
			@Override
			protected void populateItem(ListItem<CaliberOrder> item) {
				CaliberOrder caliberOrder = item.getModelObject();

				Worker worker = caliberOrder.getWorker();

				item.add(new Label("worker", worker.getFirstName() + " " + worker.getLastName()));
				item.add(new Label("deviceNr", caliberOrder.getCaliber().getDeviceNr()));

				Link<CaliberOrder> confirmLink = null;
				String confirmText = "";
				if (0 == caliberOrder.getCaliberOrderType().getId()) {
					confirmLink = new Link<CaliberOrder>("confirmLink", new Model<CaliberOrder>(caliberOrder)) {
						@Override
						public void onClick() {
							CaliberOrder co = getModelObject();
							caliberOrderLocal.approveTakeCaliberOrder(co);
							modelCaliberOrders.detach();
						}
					};
					confirmText = "Patvirtinti paiemimą";
				} else {
					confirmLink = new Link<CaliberOrder>("confirmLink", new Model<CaliberOrder>(caliberOrder)) {
						@Override
						public void onClick() {
							CaliberOrder co = getModelObject();
							caliberOrderLocal.approveReturnCaliberOrder(co);
							modelCaliberOrders.detach();
						}
					};
					confirmText = "Patvirtinti gražinimą";
				}
				confirmLink.add(new Label("confirmText", confirmText));
				item.add(confirmLink);

			}
		};

		add(pv);

	}
}

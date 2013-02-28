package net.texhad3op.web;

import java.util.List;

import net.texhad3op.ejb.entities.Caliber;
import net.texhad3op.ejb.entities.Worker;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class TrafficByCaliberSelectCaliberPage extends BasePresidentPermissedWebPage {

	public TrafficByCaliberSelectCaliberPage() {
		final IModel<List<Caliber>> modelCalibers = new LoadableDetachableModel<List<Caliber>>() {
			@Override
			protected List<Caliber> load() {
				return caliberLocal.findAll() ;
			}
		};

		add(new ListView<Caliber>("calibersList", modelCalibers) {
			@Override
			protected void populateItem(ListItem<Caliber> item) {
				Caliber caliber = item.getModelObject();

				Link<Caliber> link = new Link<Caliber>("linkCaliber", new Model<Caliber>(caliber)) {
					@Override
					public void onClick() {
						Caliber c = getModelObject();
						getCaliberTrafficSession().setSelectedCaliber(c);
						setResponsePage(CalibersTrafficPage.class);
					}

				};
				link.add(new Label("name", caliber.getDeviceNr()));
				item.add(link);

			}
		});

	}
}

package net.texhad3op.web;

import java.sql.Timestamp;
import java.util.List;

import net.texhad3op.ejb.entities.Caliber;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class CalibersPage extends BasePresidentPermissedWebPage {

	public CalibersPage() {
		final IModel<List<Caliber>> modelCaliber = new LoadableDetachableModel<List<Caliber>>() {
			@Override
			protected List<Caliber> load() {
				return caliberLocal.findAll() ;
			}
		};

		add(new Link<Caliber>("linkAdd") {
			@Override
			public void onClick() {
				Caliber c = new Caliber();
				c.setIsTaken(false);
				c.setNextCheckingTime(new Timestamp(System.currentTimeMillis()));
				getCaliberTrafficSession().setSelectedCaliber(c);
				getCaliberTrafficSession().setRealCaliberEdit(new Boolean(true));
				setResponsePage(CaliberEditPage.class);
			}

		});
		add(new ListView<Caliber>("calibersList", modelCaliber) {
			@Override
			protected void populateItem(ListItem<Caliber> item) {
				Caliber caliber = item.getModelObject();
				item.add(new Label("deviceNr", caliber.getDeviceNr()));			

				Link<Caliber> link = new Link<Caliber>("linkEdit", new Model<Caliber>(caliber)) {
					@Override
					public void onClick() {
						Caliber c = getModelObject();
						getCaliberTrafficSession().setSelectedCaliber(caliberLocal.find(c.getId()));
						getCaliberTrafficSession().setRealCaliberEdit(new Boolean(true));
						setResponsePage(CaliberEditPage.class);
					}
				};
				item.add(link);

			}
		});

	}
}




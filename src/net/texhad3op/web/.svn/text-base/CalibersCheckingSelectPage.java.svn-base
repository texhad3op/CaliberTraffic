package net.texhad3op.web;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import net.texhad3op.ejb.Utils;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class CalibersCheckingSelectPage extends BasePresidentPermissedWebPage {

	public CalibersCheckingSelectPage() {
		final IModel<List<Object[]>> modelCalibers = new LoadableDetachableModel<List<Object[]>>() {
			@Override
			protected List<Object[]> load() {
				return caliberLocal.findAllCalibersForChecking() ;
			}
		};

		add(new ListView<Object[]>("calibersList", modelCalibers) {
			@Override
			protected void populateItem(ListItem<Object[]> item) {
				Object[] tuple = item.getModelObject();
				BigInteger caliberId = (BigInteger)tuple[0];
				String deviceNr = (String)tuple[2];				
				item.add(new Label("nextCheckingTime", Utils.formatDate((Timestamp) tuple[1])));
				Link<BigInteger> link = new Link<BigInteger>("linkCaliber", new Model<BigInteger>(caliberId)) {
					@Override
					public void onClick() {
						BigInteger caliberId = getModelObject();
						getCaliberTrafficSession().setSelectedCaliber(caliberLocal.find(caliberId.longValue()));
						getCaliberTrafficSession().setRealCaliberEdit(new Boolean(false));
						setResponsePage(CaliberEditPage.class);
					}
				};
				link.add(new Label("deviceNr", deviceNr));
				item.add(link);
			}
		});
	}
}


package net.texhad3op.web;

import java.sql.Timestamp;
import java.util.List;

import net.texhad3op.ejb.Utils;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class CalibersTrafficPage extends BasePresidentPermissedWebPage {

	Image dynamicImage;

	public CalibersTrafficPage() {
		final IModel<List<Object[]>> modelCalibersTraffic = new LoadableDetachableModel<List<Object[]>>() {
			@Override
			protected List<Object[]> load() {
				return caliberLocal.allCalibersTraffic(getCaliberTrafficSession().getSelectedCaliber());
			}
		};

		add(new Label("device", getCaliberTrafficSession().getSelectedCaliber().getDeviceNr()));

		add(new ListView<Object[]>("calibersList", modelCalibersTraffic) {
			@Override
			protected void populateItem(ListItem<Object[]> item) {
				Object[] tuple = item.getModelObject();
				Integer operation = (Integer) tuple[1];
				item.add(new Label("type", null != operation ? Utils.getOperationType(operation).getName() : ""));

				Timestamp registered = (Timestamp) tuple[2];
				item.add(new Label("registered", null != registered ? registered.toString() : ""));
				String firstname = (String) tuple[4];
				item.add(new Label("firstname", null != firstname ? firstname : ""));
			}
		});
		dynamicImage = new Image("picture", String.valueOf(getCaliberTrafficSession().getSelectedCaliber().getId())) {
			@Override
			public boolean isVisible() {
				return true;// return !getDefaultModelObject().equals(new
							// BigInteger("0"));
			}
		};

		dynamicImage.add(new AttributeModifier("src", true, new AbstractReadOnlyModel() {
			@Override
			public final Object getObject() {
				String caliberId = CalibersTrafficPage.this.dynamicImage.getDefaultModelObjectAsString();
				return "caliberChart?id=" + caliberId;
			}

		}));
		dynamicImage.setOutputMarkupId(true);
		add(dynamicImage);
	}
}
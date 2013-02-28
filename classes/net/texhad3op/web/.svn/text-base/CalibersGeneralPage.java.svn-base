package net.texhad3op.web;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import net.texhad3op.ejb.Utils;
import net.texhad3op.ejb.entities.Caliber;
import net.texhad3op.ejb.entities.CaliberOrder;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class CalibersGeneralPage extends BaseWebPage {
	boolean showError = false;
	String errorMessage = "";

	public CalibersGeneralPage() {
		final IModel<List<Object[]>> modelCalibers = new LoadableDetachableModel() {
			@Override
			protected List<Object[]> load() {
				return caliberLocal.findAllCalibers(deviceNr, measurementNominalMin, measurementNominalMax, getCaliberTrafficSession()
						.getWorker());
			}
		};

		// PageableListView pv = new PageableListView<Object[]>("calibersList",
		// modelCalibers, 3) {
		ListView pv = new ListView<Object[]>("calibersList", modelCalibers) {
			@Override
			protected void populateItem(ListItem<Object[]> item) {
				Object[] tuple = item.getModelObject();
				Integer operation = (Integer) tuple[0];
				String user = (String) tuple[1];

				final Long userid = null != tuple[2] ? ((BigInteger) tuple[2]).longValue() : new Long("0");

				item.add(new Label("type", null != operation ? Utils.getOperationType(operation).getName() : ""));
				item.add(new Label("user", null != user ? user : ""));

				final Boolean isTaken = (Boolean) tuple[3];

				final BigInteger caliberId = (BigInteger) tuple[4];
				final Integer cot = (Integer) tuple[12];
				final long caliberOrderType = (null == cot ? -1 : cot.longValue());

				final Boolean isDeviceActive = (Boolean) tuple[13];
				final Long takerId = (null == (BigInteger) tuple[14] ? new Long(0) : ((BigInteger) tuple[14]).longValue());
				Boolean isUserBlocked = getCaliberTrafficSession().getWorker().getIsBlocked();
				Link<BigInteger> takeLink = new Link<BigInteger>("actionLink", new Model<BigInteger>(caliberId)) {
					@Override
					public void onClick() {
						BigInteger cid = getModelObject();
						if (!caliberLocal.takeCaliber(cid.longValue(), getCaliberTrafficSession().getWorker())) {
							Caliber caliber = caliberLocal.find(cid.longValue());
							showError = true;
							errorMessage = "Kalibras " + caliber.getDeviceNr() + " jau paimtas.";
						}
						modelCalibers.detach();
					}
				};

				Link<BigInteger> returnLink = new Link<BigInteger>("actionLink", new Model<BigInteger>(caliberId)) {
					@Override
					public void onClick() {
						BigInteger cid = getModelObject();
						caliberLocal.returnCaliber(cid.longValue(), getCaliberTrafficSession().getWorker());
						modelCalibers.detach();
					}
				};

				Link<BigInteger> orderLink = new Link<BigInteger>("actionLink", new Model<BigInteger>(caliberId)) {
					@Override
					public void onClick() {
						BigInteger cid = getModelObject();
						CaliberOrder caliberOrder = new CaliberOrder();
						caliberOrder.setCaliber(caliberLocal.find(cid.longValue()));
						caliberOrder.setCaliberOrderType(Utils.getCaliberOrderType(0));
						caliberOrder.setRegistered(new Timestamp(System.currentTimeMillis()));
						caliberOrder.setWorker(getCaliberTrafficSession().getWorker());
						caliberOrder.setIsActive(true);
						caliberOrderLocal.create(caliberOrder);

						Caliber caliber = caliberLocal.find(cid.longValue());
						caliber.setLastCaliberOrderId(caliberOrder.getId());
						caliberLocal.edit(caliber);
						modelCalibers.detach();

					}
				};

				Link<BigInteger> returnOrderLink = new Link<BigInteger>("actionLink", new Model<BigInteger>(caliberId)) {
					@Override
					public void onClick() {
						BigInteger cid = getModelObject();
						CaliberOrder caliberOrder = new CaliberOrder();
						caliberOrder.setCaliber(caliberLocal.find(cid.longValue()));
						caliberOrder.setCaliberOrderType(Utils.getCaliberOrderType(1));
						caliberOrder.setRegistered(new Timestamp(System.currentTimeMillis()));
						caliberOrder.setWorker(getCaliberTrafficSession().getWorker());
						caliberOrder.setIsActive(true);
						caliberOrderLocal.create(caliberOrder);

						Caliber caliber = caliberLocal.find(cid.longValue());
						caliber.setLastCaliberOrderId(caliberOrder.getId());
						caliberLocal.edit(caliber);
						modelCalibers.detach();
					}
				};

				Link<BigInteger> nullLink = new Link<BigInteger>("actionLink", new Model<BigInteger>(caliberId)) {
					@Override
					public void onClick() {
					}

					@Override
					public boolean isVisible() {
						return false;
					}
				};

				// // blocked
				// is taken toboj - order to return
				// not taken order to take
				//
				//
				//
				// // ne blocked
				// not taken - take
				// taken - toboj - return
				// ne toboj - nichego

				Link<BigInteger> resultLink = null;
				String actionLabel = "";

				if (isDeviceActive) {
					if (isUserBlocked) {

						if (isTaken) {
							if (userid.equals(getCaliberTrafficSession().getWorker().getId())) {
								if (1 != caliberOrderType) {
									resultLink = returnOrderLink;
									actionLabel = "užsakyti gražinti";
								} else {
									resultLink = nullLink;
									actionLabel = "";
								}
							} else {
								resultLink = nullLink;
								actionLabel = "";
							}
						} else {
							if (0 == caliberOrderType) {
								resultLink = nullLink;
								actionLabel = "";
							}
							if (-1 == caliberOrderType) {
								resultLink = orderLink;
								actionLabel = "užsakyti";
							}
							if (1 == caliberOrderType) {
								resultLink = orderLink;
								actionLabel = "užsakyti";
							}
						}
					} else {
						if (isTaken) {
							if (userid.equals(getCaliberTrafficSession().getWorker().getId())) {
								resultLink = returnLink;
								actionLabel = "gražinti";
							} else {
								resultLink = nullLink;
								actionLabel = "";
							}
						} else {
							resultLink = takeLink;
							actionLabel = "paimti";
						}
					}
				} else {
					if (isTaken) {
						if (userid.equals(getCaliberTrafficSession().getWorker().getId())) {
							resultLink = returnLink;
							actionLabel = "gražinti blogą";
						} else {
							resultLink = nullLink;
							actionLabel = "";
						}
					} else {
//						resultLink = orderLink;
//						actionLabel = "uЕѕsakyti blogД…";
						resultLink = nullLink;
						actionLabel = "";						
					}
				}
				resultLink.add(new Label("label", actionLabel));
				item.add(resultLink);

				item.add(new Label("deviceNr", String.valueOf(tuple[5])));
				item.add(new Label("isGreat", true == isDeviceActive ? "taip" : "ne"));
				item.add(new Label("measurementNominal", String.valueOf(tuple[6])));
				item.add(new Label("measurementTolerance", null == tuple[7] ? "" : String.valueOf(tuple[7])));
				item.add(new Label("minCaliber", null == tuple[8] ? "" : String.valueOf(tuple[8])));
				item.add(new Label("maxCaliber", null == tuple[9] ? "" : String.valueOf(tuple[9])));
				item.add(new Label("holdPlace", String.valueOf(tuple[10])));
				item.add(new Label("holdSubPlace", String.valueOf(tuple[11])));
				item.add(new Label("operationTime", Utils.formatDate((Timestamp) tuple[15])));				
			}
		};

		add(pv);
		// add(new PagingNavigator("navigator", pv));

		add(new Label("errorMessage", new PropertyModel<String>(this, "errorMessage")) {
			@Override
			public boolean isVisible() {
				return showError;
			}
		});

		Form form = new Form("form") {
			@Override
			protected void onSubmit() {
				modelCalibers.detach();
			}
		};
		add(form);
		final TextField<String> deviceNrText = new TextField<String>("deviceNr", new PropertyModel<String>(this, "deviceNr"));
		form.add(deviceNrText);

		final TextField<String> measurementNominalMinText = new TextField<String>("measurementNominalMin", new PropertyModel<String>(this,
				"measurementNominalMin"));
		form.add(measurementNominalMinText);

		final TextField<String> measurementNominalMaxText = new TextField<String>("measurementNominalMax", new PropertyModel<String>(this,
				"measurementNominalMax"));
		form.add(measurementNominalMaxText);

		// DropDownChoice<CompanyType> dd2 = new
		// DropDownChoice<CompanyType>("companyType", new PropertyModel(this,
		// "companyType"),
		// Utils.companyTypes, new ChoiceRenderer("name", "id"));
		// dd2.setNullValid(true);
		// form.add(dd2);
		add(form);

	}

	// CompanyType companyType;
	String deviceNr;
	String measurementNominalMin;
	String measurementNominalMax;

}

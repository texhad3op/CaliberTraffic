package net.texhad3op.web;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import net.texhad3op.ejb.entities.Caliber;

import org.apache.wicket.datetime.DateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.format.DateTimeFormatter;



public class CaliberEditPage extends BasePresidentPermissedWebPage {

	public CaliberEditPage() {

		setDefaultModel(new Model<Caliber>() {
			@Override
			public Caliber getObject() {
				return getCaliberTrafficSession().getSelectedCaliber();
			}
		});

		final TextField<String> deviceNr = new TextField<String>("deviceNr",
				new PropertyModel<String>(getDefaultModel(), "deviceNr"));

		final TextField<String> measurementNominal = new TextField<String>("measurementNominal",
				new PropertyModel<String>(getDefaultModel(), "measurementNominal"));
		
		final TextField<String> measurementTolerance = new TextField<String>("measurementTolerance",
				new PropertyModel<String>(getDefaultModel(), "measurementTolerance"));		

		final TextField<String> minCaliber = new TextField<String>("minCaliber",
				new PropertyModel<String>(getDefaultModel(), "minCaliber"));		
		
		final TextField<String> maxCaliber = new TextField<String>("maxCaliber",
				new PropertyModel<String>(getDefaultModel(), "maxCaliber"));		

		final TextField<String> holdPlace = new TextField<String>("holdPlace",
				new PropertyModel<String>(getDefaultModel(), "holdPlace"));		
		
		final TextArea<String> description = new TextArea<String>("description",
				new PropertyModel<String>(getDefaultModel(), "description"));			
		
		final TextField<String> holdSubPlace = new TextField<String>("holdSubPlace",
				new PropertyModel<String>(getDefaultModel(), "holdSubPlace"));		
		
		final CheckBox isLongType = new CheckBox("isLongType",
				new PropertyModel(getDefaultModel(), "isLongType"));		
		
		final CheckBox isActive = new CheckBox("isActive",
				new PropertyModel(getDefaultModel(), "isActive"));		
		
		final DateTextField nextCheckingTime = new DateTextField("nextCheckingTime", new PropertyModel(getDefaultModel(), "nextCheckingTime"),
				new DateConverter(true) {

					@Override
					public DateTimeFormatter getFormat() {
						return null;
					}

					@Override
					public String getDatePattern() {
						return "yyyy-MM-dd";
					}

					public Date convertToObject(String value, Locale locale) {
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						Timestamp ts2 = java.sql.Timestamp.valueOf(value + " " + ts.getHours() + ":" + ts.getMinutes() + ":"
								+ ts.getSeconds());
						return ts2;
					}

					public String convertToString(Object value, Locale locale) {
						GregorianCalendar gc = new GregorianCalendar();
						gc.setTimeInMillis(System.currentTimeMillis());
						gc.add(GregorianCalendar.MONTH, 3);

						Timestamp ts = new Timestamp(gc.getTimeInMillis());
						return ts.toString().substring(0, 10);
					}

					@Override
					public Locale getLocale() {
						return ConstantsUtil.getLocaleLt();
					}

				});

		Form<Caliber> form = new Form<Caliber>("form") {
			@Override
			protected void onSubmit() {
				if (null == getCaliberTrafficSession().getSelectedCaliber().getId()) {
					Caliber cCaliber = (Caliber) CaliberEditPage.this.getDefaultModel().getObject();
					caliberLocal.create(cCaliber);
					setResponsePage(CalibersPage.class);
				} else {
					Caliber cCaliber = (Caliber) CaliberEditPage.this.getDefaultModel().getObject();
					caliberLocal.edit(cCaliber);
					if(getCaliberTrafficSession().getRealCaliberEdit().equals(Boolean.TRUE))
						setResponsePage(CalibersPage.class);						
					else
					setResponsePage(CalibersCheckingSelectPage.class);
				}
			}
		};

		form.add(deviceNr);
		form.add(measurementNominal);
		
		form.add(measurementTolerance);
		form.add(minCaliber);
		form.add(maxCaliber);
		form.add(holdPlace);
		form.add(holdSubPlace);
		form.add(isLongType);
		form.add(isActive);
		form.add(description);		
		nextCheckingTime.add(new DatePicker());
		form.add(nextCheckingTime);
		
//		dropDownChoiceTypes.setRequired(true);
//		form.add(dropDownChoiceTypes);
//
//		form.add(new Link<Company>("back") {
//			@Override
//			public void onClick() {
//				setResponsePage(EventsPage.class);
//			}
//		});
		add(form);


	}
}

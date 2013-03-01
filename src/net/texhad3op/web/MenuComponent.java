package net.texhad3op.web;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class MenuComponent extends Panel {
	Link<String> link;
	Label label;
	String id_;

	public MenuComponent(String id, String labelString) {
		super(id);
		id_ = id;
		link = new Link<String>("menuLink") {
			@Override
			public void onClick() {
				getCaliberTrafficSession().setSelectedMenuPunkt(id_);
				onClick2();
			}
		};

		link.add(label = new Label("menuLabel", labelString));

		WebMarkupContainer targetDiv = new WebMarkupContainer("targetDiv");
		add(targetDiv);
		targetDiv.add(link);
		targetDiv.add(new SimpleAttributeModifier("class",
				getCaliberTrafficSession().getSelectedMenuPunkt().equals(id_) ? "active" : ""));
	}

	public void onClick2() {

	}

	public CaliberTrafficSession getCaliberTrafficSession() {
		return (CaliberTrafficSession) getSession();
	}
}


package net.texhad3op.web;

import net.texhad3op.ejb.entities.Worker;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.resources.StyleSheetReference;
import org.apache.wicket.model.PropertyModel;

public class LoginPage extends BasePage {
	String login;
	String password;

	public LoginPage() {

		add(new StyleSheetReference("stylesheet", BasePage.class, "style.css"));
		add(new StyleSheetReference("stylesheet2", BasePage.class,
				"css/bootstrap.min.css"));
		final TextField<String> loginField = new TextField<String>("login",
				new PropertyModel<String>(this, "login"));
		final PasswordTextField passwordField = new PasswordTextField(
				"password", new PropertyModel<String>(this, "password"));

		Form<Worker> loginForm = new Form<Worker>("loginForm") {
			@Override
			protected void onSubmit() {
				Worker worker = workerLocal.getWorker(LoginPage.this.login,
						LoginPage.this.password);
				if (null != worker) {
					getCaliberTrafficSession().setWorker(worker);
					setResponsePage(CalibersGeneralPage.class);
				}
			}
		};
		add(loginForm);
		loginForm.add(loginField);
		loginForm.add(passwordField);
	}
}

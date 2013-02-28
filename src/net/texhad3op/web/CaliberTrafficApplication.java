package net.texhad3op.web;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.texhad3op.ejb.CaliberChartItem;
import net.texhad3op.ejb.CaliberLocal;

import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.Resource;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.resource.AbstractResourceStreamWriter;
import org.apache.wicket.util.resource.IResourceStream;
import org.wicketstuff.javaee.injection.JavaEEComponentInjector;

public class CaliberTrafficApplication extends WebApplication {

	public CaliberTrafficApplication() {
	}

	@Override
	protected void init() {
		addComponentInstantiationListener(new JavaEEComponentInjector(this));
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");

		final String resourceKey = "DYN_IMG_KEY2";

		getSharedResources().add(resourceKey, new Resource() {
			@Override
			public IResourceStream getResourceStream() {
				final Long caliberId = getParameters().getLong("id");

				CaliberLocal caliberLocal = getCaliberLocal();
				List<CaliberChartItem> list = caliberLocal.allCalibersTrafficChartItems(caliberId);

				final BufferedImage img = new BufferedImage(500, 20, BufferedImage.TYPE_INT_RGB);
				final Graphics2D g2 = img.createGraphics();

				CaliberChartItem last = list.get(list.size() - 1);
				Double koef = new Double(500 / (0 == last.diff ? 1 : last.diff));
				CaliberChartItem prevItem = null;
				for (CaliberChartItem item : list) {
					if (null != prevItem) {
						Double x1 = prevItem.diff * koef;
						Double x2 = item.diff * koef;
						Rectangle2D.Double rr = new Rectangle2D.Double(x1, 0, x2, 50);
						if (prevItem.isTaken)
							g2.setColor(Color.DARK_GRAY);
						else
							g2.setColor(Color.LIGHT_GRAY);
						g2.fill(rr);
					}

					prevItem = item;
				}

				return new AbstractResourceStreamWriter() {
					public String getContentType() {
						return "image/png";
					}

					public void write(OutputStream output) {
						try {
							ImageIO.write(img, "PNG", output);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				};
			}
		});
		mountSharedResource("/caliberChart", Application.class.getName() + "/" + resourceKey);

	}

	private CaliberLocal getCaliberLocal() {
		Context context;
		CaliberLocal caliberLocal = null;
		try {
			context = new InitialContext();
			caliberLocal = (CaliberLocal) context.lookup("CaliberTraffic/CaliberEJB/local");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return caliberLocal;
	}

	@Override
	public Class<? extends WebPage> getHomePage() {
		return LoginPage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new CaliberTrafficSession(request);
	}

}

package ru.mail.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import ru.mail.server.core.DefaultServer;

import java.net.URL;

@SuppressWarnings({"Duplicates", "NotNullNullableValidation"})
public class WebApp {

    final Server server = new DefaultServer().build();

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

    context.addServlet(HttpServletDispatcher.class , "/");
    context.addEventListener(new GuiceListener());

    server.setHandler(context);
    server.start();

    final URL resource = DefaultServlet.class.getResource("/static");

}

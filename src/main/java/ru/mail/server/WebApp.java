package ru.mail.server;

import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import ru.mail.server.core.DefaultServer;
import ru.mail.server.security.SecurityHandlerBuilder;

import java.net.URL;

import static java.util.Arrays.asList;

@SuppressWarnings({"Duplicates", "NotNullNullableValidation"})
public class WebApp {
    public static void main(String[] args) throws Exception {
        final Server server = new DefaultServer().build();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);


        context.addServlet(HttpServletDispatcher.class, "/");

        context.addEventListener(new GuiceListener());

        final String hashConfig = WebApp.class.getResource("/hash_config").toExternalForm();

        final HashLoginService hashLoginService = new HashLoginService("login", hashConfig);
        final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(hashLoginService);
        server.addBean(hashLoginService);
        securityHandler.setHandler(context);



        HandlerCollection collection = new HandlerCollection();
        collection.setHandlers(new Handler[]{securityHandler, context});


        server.setHandler(collection);
        server.start();
        server.join();

    }


}

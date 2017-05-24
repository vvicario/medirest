package com.medi.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import sun.applet.Main;


public class App {

    static final String API_PATH_SPEC = "/api/*";

    public static void main( String[] args ) throws Exception {
        final int port = 8080;
        final Server server = new Server(port);
        ResourceConfig config = new ResourceConfig();
        config.packages("com.medi.app");
        config.register(JacksonFeature.class);
        ServletHolder apiServlet = new ServletHolder(new ServletContainer(config));

        // setup Application context
        ServletContextHandler context = new ServletContextHandler();
        apiServlet.setInitOrder(1);
        context.addServlet(apiServlet, API_PATH_SPEC);
        // setup static (Swagger UI) resources

        String resourceBasePath = Main.class.getResource(
                "/swagger").toExternalForm();
        context.setResourceBase(resourceBasePath);
        context.setWelcomeFiles(new String[] { "index.html" });
        ServletHolder swaggerUiServlet = new ServletHolder(new DefaultServlet());
        swaggerUiServlet.setInitOrder(2);
        context.addServlet(new ServletHolder(new DefaultServlet()), "/*");
        server.setHandler(context);
        server.start();
        server.join();
    }
}


package ru.mail.server;


import com.google.inject.AbstractModule;
import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import ru.mail.server.api.AddProductREST;
import ru.mail.server.api.DeleteProductREST;
import ru.mail.server.api.GetProductREST;
import ru.mail.server.api.WelcomeREST;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NotNullNullableValidation")
public final class GuiceListener extends GuiceResteasyBootstrapServletContextListener {

    @Override
    protected List<? extends Module> getModules(ServletContext context) {
        return Collections.singletonList(new GuiceModule());
    }


    @SuppressWarnings("rawtypes")
    private static final class GuiceModule extends AbstractModule {
        @SuppressWarnings("PointlessBinding")
        @Override
        protected void configure() {
            bind(WelcomeREST.class);
            bind(GetProductREST.class);
            bind(AddProductREST.class);
            bind(DeleteProductREST.class);
        }
    }

}

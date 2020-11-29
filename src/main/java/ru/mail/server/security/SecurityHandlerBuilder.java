package ru.mail.server.security;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.util.security.Constraint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@SuppressWarnings("NotNullNullableValidation")
public final class SecurityHandlerBuilder {
    private static final String ROLE_ADMIN = "admin";
    private static final String ROLE_GUEST = "guest";

    private final ConstraintSecurityHandler security = new ConstraintSecurityHandler();

    private static Constraint buildConstraint(String... userRoles) {
        final Constraint starterConstraint = new Constraint();
        starterConstraint.setName(Constraint.__BASIC_AUTH);
        starterConstraint.setRoles(userRoles);
        starterConstraint.setAuthenticate(true);
        return starterConstraint;
    }

    private static Collection<ConstraintMapping> constraintGetMapping(Constraint constraint,
                                                                      Collection<String> paths) {
        return constraintMapping(constraint, paths, "GET");
    }

    private static Collection<ConstraintMapping> constraintFullMapping(Constraint constraint,
                                                                       Collection<String> paths) {
        return constraintMapping(constraint, paths, "*");
    }

    private static Collection<ConstraintMapping> constraintMapping(Constraint constraint,
                                                                   Collection<String> paths,
                                                                   String method) {
        return paths.stream()
                .map(path -> {
                            final ConstraintMapping mapping = new ConstraintMapping();
                            mapping.setConstraint(constraint);
                            mapping.setPathSpec(path);
                            mapping.setMethod(method);
                            return mapping;
                        }
                ).collect(Collectors.toList());
    }

    public final ConstraintSecurityHandler build(LoginService loginService) {
        security.setLoginService(loginService);

        final List<ConstraintMapping> constraintMappings = new ArrayList<>();
        constraintMappings.addAll(constraintFullMapping(
                buildConstraint(ROLE_ADMIN),
                asList("/addProduct", "/addProduct/submit", "/delete/status", "/delete")
        ));

        constraintMappings.addAll(constraintGetMapping(
                buildConstraint(ROLE_GUEST, ROLE_ADMIN),
                asList("/","/allProducts", "/allProducts/*")
        ));

        security.setConstraintMappings(constraintMappings);
        security.setAuthenticator(new BasicAuthenticator());
        security.setDenyUncoveredHttpMethods(true);
        return security;
    }
}
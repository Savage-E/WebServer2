package ru.mail.server.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@SuppressWarnings("NotNullNullableValidation")
@Path("/help")
public class GetHelpREST {
    @GET
    public String get() {
        String response=null;
        return response;
    }
}

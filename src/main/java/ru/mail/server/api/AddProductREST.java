package ru.mail.server.api;

import com.google.inject.multibindings.ProvidesIntoMap;
import ru.mail.server.ReadWriteProducts;
import ru.mail.server.entity.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@SuppressWarnings("NotNullNullableValidation")
@Path("/addProduct")
public class AddProductREST {

    @GET
    @Produces("text/html; qs=0.1")
    public String get() {
        String response = "<!DOCTYPE HTML>\n" +
                "<html>\n" +
                "<head>\n" +
                " <meta charset=\"utf-8\">\n" +
                " <title>Add Product</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/addProduct/submit\" method=\"post\">\n" +
                " <p>Name</p>\n" +
                " <p><input type=\"text\" name=\"name\"></p>\n" +
                " <p>Manufacturer</p>\n" +
                " <p><input type=\"text\" name=\"manufacturer\"></p>\n" +
                " <p>Amount</p>\n" +
                " <p><input type=\"text\" name=\"amount\"></p>\n" +
                " <p><input type=\"submit\"></p>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";

        return response;
    }

    @POST
    @Path("/submit")
    public Response getDataFromParams(@FormParam("name") String name, @FormParam("manufacturer") String manufacturer, @FormParam("amount") int amount) throws IOException {
        Product product = new Product(name, manufacturer, amount);
        ReadWriteProducts.writeData(product);
        return Response.ok(backtoStart()).header(HttpHeaders.CACHE_CONTROL, "no-cache").build();
    }

    public String backtoStart() {
        return "<h1>File sent</h1>\n" +
                "<p><a href=\"http://127.0.0.1:3466/\">Turn back on main<p>\n";
    }


}

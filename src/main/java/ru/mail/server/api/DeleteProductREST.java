package ru.mail.server.api;


import ru.mail.server.ReadWriteProducts;
import ru.mail.server.entity.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("NotNullNullableValidation")
@Path("/delete")
public class DeleteProductREST {

    @GET
    @Produces("text/html; qs=0.1")
    public String get() {
        String response = "<!DOCTYPE HTML>\n" +
                "<html>\n" +
                "<head>\n" +
                " <meta charset=\"utf-8\">\n" +
                " <title>Delete product</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/delete/status\" method=\"post\">\n" +
                " <p>Name</p>\n" +
                " <p><input type=\"text\" name=\"name\"></p>\n" +
                " <p><input type=\"submit\"></p>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";

        return response;
    }

    @POST
    @Path("/status")
    public Response delete(@FormParam("name") String name) throws IOException {

        if (deleteProduct(name)) {
            String response = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Status</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <h1>Product deleted</h1>\n" +
                    "<p><a href=\"http://127.0.0.1:3466/\">Turn back on main<p>\n"+
                    "</body>\n" +
                    "</html>";
            return Response.ok(response).header(HttpHeaders.CACHE_CONTROL, "no-cache").build();
        } else
            return Response.status(404).build();
    }

    private boolean deleteProduct(String name) throws IOException {
        boolean res = false;
        ArrayList<Product> temp = (ArrayList<Product>) ReadWriteProducts.getProducts();
        ArrayList<Product> result = temp;

        for (Iterator<Product> iterator = temp.iterator(); iterator.hasNext(); ) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                iterator.remove();
                res = true;
            }
        }


        if (res)
            ReadWriteProducts.writeData(result);

        return res;
    }

}

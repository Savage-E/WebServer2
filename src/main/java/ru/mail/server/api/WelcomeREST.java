package ru.mail.server.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
@SuppressWarnings("NotNullNullableValidation")
@Path("/")
public class WelcomeREST {

    @GET
    public String get(){
        String response="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Welcome</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Hello there!!!!</h1>\n" +
                "    <p>Choose what you want to do: </p>\n" +
                "<p><a href=\"http://127.0.0.1:3466/allProducts\">See all products</a></p>\n" +
                "<p><a href=\"http://127.0.0.1:3466/addProduct\">Add new product(only for manager)</a></p>\n" +
                "<p><a href=\"http://127.0.0.1:3466/allProducts/Name\">See all products from specified manufacturer(write after /allProducts/ name of the manufacturer)</a></p>\n" +
                "<p><a href=\"http://127.0.0.1:3466/delete\">Delete product(only for manager)</a></p>\n" +
                "</body>\n" +
                "</html>";
        return response;
    }
}

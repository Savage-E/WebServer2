package ru.mail.server.api;

import jdk.nashorn.internal.objects.annotations.Getter;
import ru.mail.server.ReadWriteProducts;
import ru.mail.server.entity.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static ru.mail.server.ReadWriteProducts.getProducts;

@SuppressWarnings("NotNullNullableValidation")
@Path("/allProducts")
public class GetProductREST {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(getProducts()).header(HttpHeaders.CACHE_CONTROL, "no-cache").build();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSome(@PathParam("name") String name) {
        return Response.ok(getSpecProducts(name)).header(HttpHeaders.CACHE_CONTROL, "no-cache").build();
    }


    private ArrayList<Product> getSpecProducts(String name){
        List<Product> temp = getProducts();
        if (temp.isEmpty())
            return null;
        System.out.println(temp.get(0));

        ArrayList<Product> result= new ArrayList<>();

        for (Product p : temp
        ) {
            if(p.getManufacturer().equals(name)){
                result.add(p);
            }
        }

        return result;
    }
}

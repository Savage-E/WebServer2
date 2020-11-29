package ru.mail.server;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.mail.server.entity.Product;

import java.io.IOException;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class ReadWriteProducts {

    public static List<Product> getProducts() {

        ObjectMapper mapper = new ObjectMapper();

        List<Product> list = null;
        try {
            list = Arrays.asList(mapper.readValue(Paths.get("src\\main\\resources\\data\\products.json").toFile(), Product[].class));
            ArrayList<Product> arrayList = new ArrayList<>(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Product> arrayList = new ArrayList<>(list);

        return arrayList;
    }


    public static void writeData(Product newProduct) throws IOException {
        ArrayList<Product> list = new ArrayList<>(getProducts());
        list.add(newProduct);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(Paths.get("src/main/resources/data/products.json").toFile(), list);

    }

    public static void writeData(List<Product> products) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(Paths.get("src/main/resources/data/products.json").toFile(), products);
    }

}

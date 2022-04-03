package tech.woodandsafety.unserialjavademo.service;

import org.springframework.stereotype.Service;
import tech.woodandsafety.unserialjavademo.bean.Product;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoProductService implements ProductService {

    private final Map<Integer, Product> products;

    public DemoProductService() {

        String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales ut massa id elementum. Fusce convallis nec nulla vitae congue. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nulla quam odio, suscipit sed faucibus sed, maximus eget ligula. Cras ornare metus id erat placerat, sit amet ullamcorper nisl blandit. Morbi et ante nec ex dignissim commodo. Etiam elementum magna vel massa blandit, vitae accumsan orci ultricies.";

        products = new HashMap<>();
        products.put(1, new Product(1, "keyboard", lorem, "product1"));
        products.put(2, new Product(2, "mouse", lorem, "product2"));
        products.put(3, new Product(3, "webcam", lorem, "product3"));
        products.put(4, new Product(4, "speakers", lorem, "product4"));
        products.put(5, new Product(5, "router", lorem, "product5"));
        products.put(6, new Product(6, "hard disk", lorem, "product6"));
        products.put(7, new Product(7, "rams", lorem, "product7"));
        products.put(8, new Product(8, "bettery", lorem, "product8"));
        products.put(9, new Product(9, "drive", lorem, "product9"));
    }

    @Override
    public Product get(int id) {
        return products.get(id);
    }

    @Override
    public Product[] all() {
        return this.products.values().toArray(new Product[0]);
    }
}

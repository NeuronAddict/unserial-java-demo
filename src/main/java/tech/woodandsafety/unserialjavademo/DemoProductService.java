package tech.woodandsafety.unserialjavademo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoProductService implements ProductService {

    private final Map<Integer, Product> products;

    public DemoProductService() {
        products = new HashMap<>();
        products.put(1, new Product(1, "keyboard", "bla", "product1"));
        products.put(2, new Product(2, "mouse", "bla", "product2"));
        products.put(3, new Product(3, "webcam", "bla", "product3"));
        products.put(4, new Product(4, "speakers", "bla", "product4"));
        products.put(5, new Product(5, "router", "bla", "product5"));
        products.put(6, new Product(6, "hard disk", "bla", "product6"));
        products.put(7, new Product(7, "rams", "bla", "product7"));
        products.put(8, new Product(8, "bettery", "bla", "product8"));
        products.put(9, new Product(9, "drive", "bla", "product9"));
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

package tech.woodandsafety.unserialjavademo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoProductService implements ProductService {

    private final Map<Integer, Product> products;

    public DemoProductService() {
        products = new HashMap<>();
        products.put(1, new Product(1, "laptop", "bla", "leptop"));
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

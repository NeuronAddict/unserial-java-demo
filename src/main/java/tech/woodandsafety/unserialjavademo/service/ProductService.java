package tech.woodandsafety.unserialjavademo.service;

import tech.woodandsafety.unserialjavademo.bean.Product;

public interface ProductService {
    Product get(int id);

    Product[] all();
}

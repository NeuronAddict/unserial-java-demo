package tech.woodandsafety.unserialjavademo;

public interface ProductService {
    Product get(int id);

    Product[] all();
}

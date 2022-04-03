package tech.woodandsafety.unserialjavademo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {
    private int id;
    private final String name;
    private final String description;
    private final String image;
}

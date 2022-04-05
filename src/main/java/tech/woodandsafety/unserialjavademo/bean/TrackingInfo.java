package tech.woodandsafety.unserialjavademo.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class TrackingInfo implements Serializable {

    private final List<Integer> visited;

    public TrackingInfo() {
        this.visited = new ArrayList<>();
    }

    public void addProductId(int productId) {
        this.visited.add(productId);
    }
}

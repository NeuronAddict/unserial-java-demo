package tech.woodandsafety.unserialjavademo.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class TrackingInfo implements Serializable {

    private final String ip;
    private final String userAgent;
    private final String url;

    public TrackingInfo(String ip, String userAgent, String url) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.url = url;
    }

    private int productId;

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

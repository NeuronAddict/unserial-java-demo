package tech.woodandsafety.unserialjavademo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class TrackingInfo implements Serializable {

    private final String ip;
    private final String userAgent;
    private final String url;

}

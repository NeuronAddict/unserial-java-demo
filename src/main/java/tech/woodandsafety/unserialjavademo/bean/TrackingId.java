package tech.woodandsafety.unserialjavademo.bean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;


@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class TrackingId implements Serializable {
    private final String remoteAddress;
    private final String userAgent;

}

package tech.woodandsafety.unserialjavademo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.woodandsafety.unserialjavademo.bean.TrackingId;
import tech.woodandsafety.unserialjavademo.bean.TrackingInfo;
import tech.woodandsafety.unserialjavademo.tools.SafeObjectInputStream;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TrackingService {

    private final boolean safe;

    private final Map<TrackingId, TrackingInfo> trackingInfoMap;

    public TrackingService(@Value("${tech.woodandsafety.unserial.safe}") boolean safe) {
        this.safe = safe;
        this.trackingInfoMap = new HashMap<>();
    }

    private ObjectInputStream getObjectInputStream(ByteArrayInputStream bis, boolean safe) throws IOException {
        if(safe) return new SafeObjectInputStream<>(bis, TrackingId.class);
        else return new ObjectInputStream(bis);
    }

    public String getTrackingId(String remoteAddress, String userAgent) throws IOException {
        log.info(String.format("Got user agent : %s for address %s", userAgent, remoteAddress));
        TrackingId id = new TrackingId(remoteAddress, userAgent);

        try(
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos)
        ){
            oos.writeObject(id);
            return new String(Base64.getEncoder().encode(bos.toByteArray()));
        }
    }

    public TrackingInfo updateTrackingInfo(String encodedTrackingId, int productId) throws IOException, ClassNotFoundException {
        byte[] decoded = Base64.getDecoder().decode(encodedTrackingId);

        try(
            ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
            ObjectInputStream ois = getObjectInputStream(bis, safe)) {
            Object o = ois.readObject();
            TrackingId ti = (TrackingId) o;
            if(!this.trackingInfoMap.containsKey(ti)) {
                this.trackingInfoMap.put(ti, new TrackingInfo());
            }
            TrackingInfo trackingInfo = this.trackingInfoMap.get(ti);
            trackingInfo.addProductId(productId);
            return trackingInfo;
        }
    }
}

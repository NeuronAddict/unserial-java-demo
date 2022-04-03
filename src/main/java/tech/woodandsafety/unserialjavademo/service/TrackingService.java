package tech.woodandsafety.unserialjavademo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.woodandsafety.unserialjavademo.bean.TrackingInfo;
import tech.woodandsafety.unserialjavademo.tools.LookAheadObjectInputStream;

import java.io.*;
import java.util.Base64;

@Service
@Slf4j
public class TrackingService {

    private final boolean safe;

    public TrackingService(@Value("${tech.woodandsafety.unserial.safe}") boolean safe) {
        this.safe = safe;
    }

    private ObjectInputStream getObjectInputStream(ByteArrayInputStream bis, boolean safe) throws IOException {
        if(safe) return new LookAheadObjectInputStream(bis);
        else return new ObjectInputStream(bis);
    }

    public String getTrackingInfo(String remoteAddress, String userAgent) throws IOException {
        log.info(String.format("Got user agent : %s for address %s", userAgent, remoteAddress));
        TrackingInfo ti = new TrackingInfo(remoteAddress, userAgent, "/");
        try(
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
        ){
            oos.writeObject(ti);
            return new String(Base64.getEncoder().encode(bos.toByteArray()));
        }
    }

    public void readTrackingInfo(String data, int productId) throws IOException, ClassNotFoundException {
        byte[] decoded = Base64.getDecoder().decode(data);

        try(
            ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
            ObjectInputStream ois = getObjectInputStream(bis, safe)) {
            Object o = ois.readObject();
            TrackingInfo ti = (TrackingInfo) o;
            ti.setProductId(productId);
            System.out.println(ti);
            // send ti to tracking system ...
        }
    }
}

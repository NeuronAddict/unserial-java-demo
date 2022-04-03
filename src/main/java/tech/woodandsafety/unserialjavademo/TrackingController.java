package tech.woodandsafety.unserialjavademo;

import org.securitywhitepapers.deserialization.LookAheadObjectInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

@RestController("/")
public class TrackingController {

    private final boolean safe;

    public TrackingController(@Value("${tech.woodandsafety.unserial.safe}") boolean safe) {
        this.safe = safe;
    }

    private ObjectInputStream getObjectInputStream(ByteArrayInputStream bis, boolean safe) throws IOException {
        if(safe) return new LookAheadObjectInputStream(bis);
        else return new ObjectInputStream(bis);
    }

    @PostMapping("track")
    public void sendTrackingInfo(@RequestParam String data) throws IOException, ClassNotFoundException {
        byte[] decoded = Base64.getDecoder().decode(data);

        try(
            ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
            ObjectInputStream ois = getObjectInputStream(bis, safe)) {
            Object o = ois.readObject();
            TrackingInfo ti = (TrackingInfo) o;

            System.out.println(ti);
            // send ti...
        }
    }
}

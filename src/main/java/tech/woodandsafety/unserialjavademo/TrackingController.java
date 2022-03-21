package tech.woodandsafety.unserialjavademo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

@RestController("/")
public class TrackingController {

    @PostMapping("track")
    public void sendTrackingInfo(@RequestParam String data) throws IOException, ClassNotFoundException {
        byte[] decoded = Base64.getDecoder().decode(data);

        try(
            ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
            ObjectInputStream ois = new ObjectInputStream(bis)) {
            Object o = ois.readObject();
            TrackingInfo ti = (TrackingInfo) o;

            System.out.println(ti);
            // send ti...
        }
    }
}

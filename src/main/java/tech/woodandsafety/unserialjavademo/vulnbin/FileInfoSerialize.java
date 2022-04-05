package tech.woodandsafety.unserialjavademo.vulnbin;

import tech.woodandsafety.unserialjavademo.tools.DataEncoder;
import tech.woodandsafety.unserialjavademo.tools.SerializeEncoder;

import java.io.IOException;

public class FileInfoSerialize {

    public static void main(String[] args) throws IOException, DataEncoder.EncodeException, DataEncoder.DecodeException {
        FileInfo fi = new FileInfo("/etc/passwd");
        SerializeEncoder<FileInfo> encoder = new SerializeEncoder<>();

        String encoded = encoder.encode(fi);
        System.out.println(encoded);

        FileInfo decoded = encoder.decode(encoded);

        System.out.println(decoded);

    }

}

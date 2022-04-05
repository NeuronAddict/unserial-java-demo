package tech.woodandsafety.unserialjavademo.vulnbin;

import tech.woodandsafety.unserialjavademo.tools.DataEncoder;
import tech.woodandsafety.unserialjavademo.tools.SerializeEncoder;

import java.io.IOException;

public class FileInfoSerialize {

    public static void main(String[] args) throws IOException, DataEncoder.EncodeException {
        FileInfo fi = new FileInfo("/etc/passwd");
        SerializeEncoder<FileInfo> encoder = new SerializeEncoder<>();

        System.out.println(encoder.encode(fi));

    }

}

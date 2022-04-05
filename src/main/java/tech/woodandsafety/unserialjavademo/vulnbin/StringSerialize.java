package tech.woodandsafety.unserialjavademo.vulnbin;

import tech.woodandsafety.unserialjavademo.tools.DataEncoder;
import tech.woodandsafety.unserialjavademo.tools.SerializeEncoder;

public final class StringSerialize {

    public static void main(String[] args) throws DataEncoder.EncodeException {
        SerializeEncoder<String> encoder = new SerializeEncoder<>();
        System.out.println(encoder.encode("coucou"));
    }

}

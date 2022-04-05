package tech.woodandsafety.unserialjavademo.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerializeEncoderTest {

    @Test
    public void testEncodeAndDecode() throws DataEncoder.EncodeException, DataEncoder.DecodeException {
        Integer o = 12;
        SerializeEncoder<Integer> encoder = new SerializeEncoder<>();
        String encoded = encoder.encode(o);
        Integer decoded = encoder.decode(encoded);
        assertEquals(o, decoded);
    }
}
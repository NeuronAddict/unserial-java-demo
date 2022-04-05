package tech.woodandsafety.unserialjavademo.tools;

import java.io.*;
import java.util.Base64;

public class SerializeEncoder<T> implements DataEncoder<T> {

    private final Class<T> allowedClass;

    public SerializeEncoder() {
        this.allowedClass = null;
    }

    public SerializeEncoder(Class<T> allowedClass) {
        this.allowedClass = allowedClass;
    }

    private ObjectInputStream getObjectInputStream(ByteArrayInputStream bis, Class<T> allowedClass) throws IOException {
        if(allowedClass != null) return new SafeObjectInputStream<>(bis, allowedClass);
        else return new ObjectInputStream(bis);
    }

    @Override
    public String encode(T data) throws EncodeException {
        try(
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos)
        ){
            oos.writeObject(data);
            return new String(Base64.getEncoder().encode(bos.toByteArray()));
        } catch (IOException e) {
            throw new EncodeException(e);
        }
    }

    @Override
    public T decode(String encodedData) throws DecodeException {

        byte[] decoded = Base64.getDecoder().decode(encodedData);

        try(
                ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
                ObjectInputStream ois = getObjectInputStream(bis, allowedClass)) {
            @SuppressWarnings("unchecked") T o = (T) ois.readObject(); // <---
            return o;
        } catch (IOException | ClassNotFoundException e) {
            throw new DecodeException(e);
        }
    }
}

package tech.woodandsafety.unserialjavademo.tools;

import java.io.*;
import java.util.Objects;

public class SafeObjectInputStream<T> extends ObjectInputStream {

    private final Class<T> allowedType;

    public SafeObjectInputStream(InputStream inputStream, Class<T> allowedType) throws IOException {
        super(inputStream);
        this.allowedType = allowedType;
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {

        if (!Objects.equals(desc.getName(), this.allowedType.getName()))
            throw new InvalidClassException("Unauthorized deserialization attempt", desc.getName());

        return super.resolveClass(desc);
    }
}
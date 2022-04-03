package tech.woodandsafety.unserialjavademo.tools;

import tech.woodandsafety.unserialjavademo.bean.TrackingInfo;

import java.io.*;
import java.util.ArrayList;

public class LookAheadObjectInputStream extends ObjectInputStream {

    ArrayList<String> allowedTypes;

    public LookAheadObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
        allowedTypes = new ArrayList<>();
        allowedTypes.add(TrackingInfo.class.getName());
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {

        if (!allowedTypes.contains(desc.getName()))
            throw new InvalidClassException("Unauthorized deserialization attempt", desc.getName());

        return super.resolveClass(desc);
    }
}
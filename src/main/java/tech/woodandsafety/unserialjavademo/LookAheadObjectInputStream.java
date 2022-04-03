package org.securitywhitepapers.deserialization;

import tech.woodandsafety.unserialjavademo.TrackingInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
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
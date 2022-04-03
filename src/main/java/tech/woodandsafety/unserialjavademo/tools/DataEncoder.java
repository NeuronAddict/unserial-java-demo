package tech.woodandsafety.unserialjavademo.tools;

public interface DataEncoder<T> {

    String encode(T data) throws EncodeException;
    T decode(String encodedData) throws DecodeException;

    class EncodeException extends Exception {
        public EncodeException() {
        }

        public EncodeException(String message) {
            super(message);
        }

        public EncodeException(String message, Throwable cause) {
            super(message, cause);
        }

        public EncodeException(Throwable cause) {
            super(cause);
        }

        public EncodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

    class DecodeException extends Exception {

        public DecodeException() {
        }

        public DecodeException(String message) {
            super(message);
        }

        public DecodeException(String message, Throwable cause) {
            super(message, cause);
        }

        public DecodeException(Throwable cause) {
            super(cause);
        }

        public DecodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}

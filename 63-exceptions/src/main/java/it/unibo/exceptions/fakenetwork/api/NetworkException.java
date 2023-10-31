package it.unibo.exceptions.fakenetwork.api;

import java.io.IOException;
import java.util.Objects;

public class NetworkException extends IOException{

    public NetworkException() {
        super("Network error: no response");
    }

    public NetworkException(final String s) {
        super("Network error while sending message: " + s);
        Objects.requireNonNull(s);
    }
}

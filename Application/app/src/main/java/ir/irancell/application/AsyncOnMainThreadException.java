package ir.irancell.application;

/**
 * Created by SKings (samanf74@gmail.com) on 8/8/2017.
 */

public class AsyncOnMainThreadException extends RuntimeException {

    public AsyncOnMainThreadException() {
        super();
    }

    public AsyncOnMainThreadException(String message) {
        super(message);
    }

    public AsyncOnMainThreadException(String message, Throwable cause) {
        super(message, cause);
    }
}

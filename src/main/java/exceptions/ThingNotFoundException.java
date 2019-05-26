package exceptions;

public class ThingNotFoundException extends Exception {
    public ThingNotFoundException(String message) {
        super(message);
    }
}

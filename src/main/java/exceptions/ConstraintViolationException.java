package exceptions;

public class ConstraintViolationException extends Exception {
    public ConstraintViolationException(String ex) {
        super(ex);
    }
}

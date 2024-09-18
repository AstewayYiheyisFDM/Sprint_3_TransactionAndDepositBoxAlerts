package exercises.javadoc_exercise;

/**
 * Exception indicating an invalid operation was attempted on a bank account.
 */
public class InvalidAccountException extends Exception {
    /**
     * Creates an InvalidAccountException with a specified detail message.
     *
     * @param message the detailed message explaining the reason for the exception
     */
    public InvalidAccountException(String message) {
        super(message);
    }
}

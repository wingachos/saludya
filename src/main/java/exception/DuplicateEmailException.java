package exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) { super("Ya existe un doctor con el correo: " + email); }
}


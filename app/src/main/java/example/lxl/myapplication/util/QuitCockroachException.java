package example.lxl.myapplication.util;


final class QuitCockroachException extends RuntimeException {
    public QuitCockroachException(String message) {
        super(message);
    }
}

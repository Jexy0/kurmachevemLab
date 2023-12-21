package tech.reliab.course.kurmachevem.bank.exception;

public class ExportException extends RuntimeException {
    public ExportException() {
        super("Error: can't export");
    }

    public ExportException(String msg) {
        super("Error: can't export: " + msg);
    }
}
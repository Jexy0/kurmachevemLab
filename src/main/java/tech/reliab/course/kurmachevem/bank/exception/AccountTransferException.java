package tech.reliab.course.kurmachevem.bank.exception;

public class AccountTransferException extends RuntimeException {
    public AccountTransferException() {
        super("Error: can't transfer account");
    }

    public AccountTransferException(String msg) {
        super("Error: can't transfer account: " + msg);
    }
}
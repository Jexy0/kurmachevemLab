package tech.reliab.course.kurmachevem.bank.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
        super("Error: not enough money");
    }
}
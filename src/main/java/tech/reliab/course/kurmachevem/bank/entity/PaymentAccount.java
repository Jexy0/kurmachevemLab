package tech.reliab.course.kurmachevem.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentAccount extends Account {
    private BigDecimal balance;

    public PaymentAccount() {
        super();
        initWithDefaults();
    }

    public PaymentAccount(PaymentAccount paymentAccount) {
        super(paymentAccount.id, paymentAccount.user, paymentAccount.bank);
        this.balance = paymentAccount.balance;
    }

    public PaymentAccount(User user, Bank bank, BigDecimal balance) {
        super(user, bank);
        this.balance = balance;
    }

    public PaymentAccount(UUID id, User user, Bank bank, BigDecimal balance) {
        super(id, user, bank);
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
                "\n account='" + super.toString() + "'" +
                ",\n balance='" + getBalance() + "'" +
                "\n}";
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    private void initWithDefaults() {
        balance = new BigDecimal("0");
    }

}
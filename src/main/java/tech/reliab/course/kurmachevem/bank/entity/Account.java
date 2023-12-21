package tech.reliab.course.kurmachevem.bank.entity;

import java.util.UUID;

public class Account {
    protected UUID id;
    protected User user;
    protected Bank bank;

    public Account() {
        initWithDefaults();
    }

    public Account(User user, Bank bank) {
        initWithDefaults();
        this.bank = bank;
    }

    public Account(UUID id, User user, Bank bank) {
        this.id = id;
        this.user = user;
        this.bank = bank;
    }

    public Account(Account account) {
        this.id = UUID.fromString(account.id.toString());
        this.user = new User(account.user);
        this.bank = new Bank(account.bank);
    }

    @Override
    public String toString() {
        return "{" +
                "\n id='" + getId() + "'" +
                ",\n client='" + getClient() + "'" +
                ",\n bank='" + getBank() + "'" +
                "\n}";
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getClient() {
        return this.user;
    }

    public void setClient(User user) {
        this.user = user;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    private void initWithDefaults() {
        id = UUID.randomUUID();
        user = null;
        bank = null;
    }

}
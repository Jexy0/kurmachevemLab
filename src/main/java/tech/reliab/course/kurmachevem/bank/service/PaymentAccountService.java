package tech.reliab.course.kurmachevem.bank.service;

import tech.reliab.course.kurmachevem.bank.entity.PaymentAccount;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentAccountService {
    PaymentAccount create(PaymentAccount paymentAccount);

    public void printPaymentData(int id);

    public PaymentAccount getPaymentAccountById(int id);

    public List<PaymentAccount> getAllPaymentAccounts();

    boolean depositMoney(PaymentAccount paymentAccount, BigDecimal amount);

    boolean withdrawMoney(PaymentAccount paymentAccount, BigDecimal amount);
}

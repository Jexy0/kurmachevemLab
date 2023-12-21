package tech.reliab.course.kurmachevem.bank.service;

import tech.reliab.course.kurmachevem.bank.entity.CreditAccount;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount);

    boolean makeMonthlyPayment(CreditAccount creditAccount);
}
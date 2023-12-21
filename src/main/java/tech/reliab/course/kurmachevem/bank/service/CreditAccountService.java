package tech.reliab.course.kurmachevem.bank.service;

import java.util.List;

import tech.reliab.course.kurmachevem.bank.entity.CreditAccount;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount);

    public CreditAccount getCreditAccountById(int id);

    public List<CreditAccount> getAllCreditAccounts();

    boolean makeMonthlyPayment(CreditAccount creditAccount);
}
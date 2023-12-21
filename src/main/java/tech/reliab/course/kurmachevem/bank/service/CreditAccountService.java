package tech.reliab.course.kurmachevem.bank.service;

import java.util.List;

import tech.reliab.course.kurmachevem.bank.entity.CreditAccount;
import tech.reliab.course.kurmachevem.bank.exception.NotEnoughMoneyException;
import tech.reliab.course.kurmachevem.bank.exception.NotFoundException;
import tech.reliab.course.kurmachevem.bank.exception.NotUniqueIdException;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount) throws NotFoundException, NotUniqueIdException;

    public CreditAccount getCreditAccountById(int id) throws NotFoundException;

    public List<CreditAccount> getAllCreditAccounts();

    boolean makeMonthlyPayment(CreditAccount creditAccount) throws NotEnoughMoneyException;
}
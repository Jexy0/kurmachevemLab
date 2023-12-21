package tech.reliab.course.kurmachevem.bank.service;

import java.math.BigDecimal;
import java.util.List;

import tech.reliab.course.kurmachevem.bank.entity.BankAtm;
import tech.reliab.course.kurmachevem.bank.exception.NotEnoughMoneyException;
import tech.reliab.course.kurmachevem.bank.exception.NotFoundException;
import tech.reliab.course.kurmachevem.bank.exception.NotUniqueIdException;

public interface AtmService {
    BankAtm create(BankAtm bankAtm) throws NotFoundException, NotUniqueIdException;

    public BankAtm getBankAtmById(int id) throws NotFoundException;

    public List<BankAtm> getAllBankAtms();

    boolean depositMoney(BankAtm bankAtm, BigDecimal amount);

    boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount) throws NotEnoughMoneyException;

    public boolean isAtmSuitable(BankAtm bankAtm, BigDecimal money);
}
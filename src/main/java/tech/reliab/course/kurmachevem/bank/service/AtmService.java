package tech.reliab.course.kurmachevem.bank.service;

import tech.reliab.course.kurmachevem.bank.entity.BankAtm;
import tech.reliab.course.kurmachevem.bank.entity.Employee;

import java.math.BigDecimal;

public interface AtmService {
    BankAtm create(BankAtm bankAtm);
    void fix(BankAtm bankAtm);
    public boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount);
    public boolean depositMoney(BankAtm bankAtm, BigDecimal amount);
    public void changeEmployee(BankAtm bankAtm, Employee employee);
}

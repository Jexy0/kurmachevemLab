package tech.reliab.course.kurmachevem.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.kurmachevem.bank.entity.BankAtm;
import tech.reliab.course.kurmachevem.bank.entity.Employee;
import tech.reliab.course.kurmachevem.bank.service.AtmService;

public class AtmServiceImpl implements AtmService {

    @Override
    public BankAtm create(BankAtm bankAtm) {
        if (bankAtm == null) {
            return null;
        }
        if (bankAtm.getTotalMoney().signum() < 0) {
            System.err.println("Error: cannot create BankAtm - totalMoney must be non-negative");
            return null;
        }
        if (bankAtm.getMaintenanceCost().signum() < 0) {
            System.err.println("Error: cannot create BankAtm - maintenanceCost must be non-negative");
            return null;
        }
        if (bankAtm.getBankOffice() == null) {
            System.err.println("Error: cannot create BankAtm - bankOffice cannot be null");
            return null;
        }
        return new BankAtm(bankAtm);
    }

    @Override
    public void fix(BankAtm bankAtm) {
        if (bankAtm.isIsCashWithdrawalAvailable()) {
            bankAtm.setWorkingStatus(BankAtm.WorkingStatus.WORKING);
        }
    }
    @Override
    public boolean depositMoney(BankAtm bankAtm, BigDecimal amount) {
        if (bankAtm == null) {
            System.err.println("Error: BankAtm cannot deposit money - non existing ATM");
            return false;
        }
        if (amount.signum() <= 0) {
            System.err.println("Error: BankAtm cannot deposit money - amount is not positive");
            return false;
        }
        if (!bankAtm.getIsCashDepositAvailable()) {
            System.err.println("Error: BankAtm cannot deposit money - deposit is not allowed");
            return false;
        }
        bankAtm.setTotalMoney(bankAtm.getTotalMoney().add(amount));
        // TODO: Добавить механизм взаимодействия с банком и офисом
        return true;
    }

    @Override
    public boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount) {
        if (bankAtm == null) {
            System.err.println("Error: BankAtm cannot withdraw money - non existing ATM");
            return false;
        }
        if (amount.signum() <= 0) {
            System.err.println("Error: BankAtm cannot withdraw money - amount is not positive");
            return false;
        }
        if (!bankAtm.getIsCashDepositAvailable()) {
            System.err.println("Error: BankAtm cannot withdraw money - deposit is not allowed");
            return false;
        }
        if (bankAtm.getTotalMoney().compareTo(amount) < 0) {
            System.err.println("Error: BankAtm cannot withdraw money - ATM does not have enough money");
            return false;
        }
        bankAtm.setTotalMoney(bankAtm.getTotalMoney().subtract(amount));
        // TODO: Добавить механизм взаимодействия с банком и офисом
        return true;
    }

    @Override
    public void changeEmployee(BankAtm bankAtm, Employee employee) {
        if (employee != null)
            bankAtm.setEmployee(employee);
        else {
            System.err.println("Error: Employee cannot be changed - non existing Employee");
            return;
        }
    }
}
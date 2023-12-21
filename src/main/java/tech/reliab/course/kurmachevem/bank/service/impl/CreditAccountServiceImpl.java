package tech.reliab.course.kurmachevem.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.kurmachevem.bank.entity.CreditAccount;
import tech.reliab.course.kurmachevem.bank.service.CreditAccountService;

public class CreditAccountServiceImpl implements CreditAccountService {

    @Override
    public CreditAccount create(CreditAccount creditAccount) {
        if (creditAccount == null) {
            return null;
        }

        if (creditAccount.getMonthCount() < 1) {
            System.err.println("Error: CreditAccount - monthCount must be at least 1");
            return null;
        }

        if (creditAccount.getCreditAmount().signum() <= 0) {
            System.err.println("Error: CreditAccount - creditAmount must be positive");
            return null;
        }

        if (creditAccount.getBank() == null) {
            System.err.println("Error: CreditAccount - no bank");
            return null;
        }

        // TODO: Возможно добавление дополнительных механизмов - расчет параметров
        // кредита и т.п.

        return new CreditAccount(creditAccount);
    }

    @Override
    public boolean makeMonthlyPayment(CreditAccount creditAccount) {
        if (creditAccount == null || creditAccount.getPaymentAccount() == null) {
            System.err.println("Error: CreditAccount - no account to take money from");
            return false;
        }

        final BigDecimal monthlyPayment = creditAccount.getMonthlyPayment();
        final BigDecimal paymentAccountBalance = creditAccount.getPaymentAccount().getBalance();

        if (paymentAccountBalance.compareTo(monthlyPayment) < 0) {
            System.err.println("Error: CreditAccount - not enough balance for monthly payment");
            return false;
        }

        creditAccount.getPaymentAccount().setBalance(paymentAccountBalance.subtract(monthlyPayment));
        creditAccount.setRemainingCreditAmount(creditAccount.getRemainingCreditAmount().subtract(monthlyPayment));

        return true;
    }

}
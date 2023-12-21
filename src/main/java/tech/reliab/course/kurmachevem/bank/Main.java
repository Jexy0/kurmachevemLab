package tech.reliab.course.kurmachevem.bank;

import tech.reliab.course.kurmachevem.bank.entity.*;
import tech.reliab.course.kurmachevem.bank.service.*;
import tech.reliab.course.kurmachevem.bank.service.impl.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class Main {
    public static <ClientService> void main(String[] args) {
        Locale.setDefault(Locale.US);

        BankService bankService = new BankServiceImpl();
        Bank bank = bankService.create(new Bank("Betrayed Expectations Bank"));
        System.out.println(bank);

        BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
        BankOffice bankOffice = bankOfficeService.create(new BankOffice(
                "Main office of Betrayed Expectations Bank",
                "Panama City, FL 32401",
                bank,
                true,
                true,
                0,
                true,
                true,
                true,
                bank.getTotalMoney(),
                new BigDecimal("900")));
        System.out.println(bankOffice);

        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService
                .create(new Employee("Tycho Nestoris", LocalDate.of(270, 2, 21), Employee.Job.Worker, bank, true,
                        bankOffice, true, new BigDecimal("10")));
        System.out.println(employee);

        AtmService atmService = new AtmServiceImpl();
        BankAtm bankAtm = atmService.create(new BankAtm("First ATM of Panama City", bankOffice.getAddress(), BankAtm.WorkingStatus.WORKING, BankAtm.MoneyStatus.NO_MONEY, bank,
                bankOffice, employee, true, true, new BigDecimal("0"), new BigDecimal("25")));
        System.out.println(bankAtm);

        UserService userService = new UserServiceImpl();
        User user = userService
                .create(new User("Teresa W. Robb", LocalDate.of(264, 2, 15), "Dragon Stone",
                        new BigDecimal("1000"), bank, new BigDecimal("999999999")));
        System.out.println(user);

        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
        PaymentAccount paymentAccount = paymentAccountService
                .create(new PaymentAccount(user, bank, new BigDecimal("9000")));
        System.out.println(paymentAccount);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        CreditAccount creditAccount = creditAccountService.create(new CreditAccount(user, bank,
                LocalDate.of(298, 1, 1), LocalDate.of(302, 1, 1), 48, new BigDecimal("29000"),
                new BigDecimal("29000"),
                new BigDecimal("100"), new BigDecimal("2"), employee, paymentAccount));
        System.out.println(creditAccount);
    }
}

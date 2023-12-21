package tech.reliab.course.kurmachevem.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import tech.reliab.course.kurmachevem.bank.entity.Bank;
import tech.reliab.course.kurmachevem.bank.entity.BankAtm;
import tech.reliab.course.kurmachevem.bank.entity.BankOffice;
import tech.reliab.course.kurmachevem.bank.entity.Client;
import tech.reliab.course.kurmachevem.bank.entity.CreditAccount;
import tech.reliab.course.kurmachevem.bank.entity.Employee;
import tech.reliab.course.kurmachevem.bank.entity.PaymentAccount;
import tech.reliab.course.kurmachevem.bank.exception.CreditException;
import tech.reliab.course.kurmachevem.bank.exception.NoPaymentAccountException;
import tech.reliab.course.kurmachevem.bank.exception.NotFoundException;
import tech.reliab.course.kurmachevem.bank.exception.NotUniqueIdException;
import tech.reliab.course.kurmachevem.bank.service.AtmService;
import tech.reliab.course.kurmachevem.bank.service.BankOfficeService;
import tech.reliab.course.kurmachevem.bank.service.BankService;
import tech.reliab.course.kurmachevem.bank.service.CreditAccountService;
import tech.reliab.course.kurmachevem.bank.service.EmployeeService;
import tech.reliab.course.kurmachevem.bank.service.ClientService;
import tech.reliab.course.kurmachevem.bank.service.PaymentAccountService;
import tech.reliab.course.kurmachevem.bank.service.impl.AtmServiceImpl;
import tech.reliab.course.kurmachevem.bank.service.impl.BankOfficeServiceImpl;
import tech.reliab.course.kurmachevem.bank.service.impl.BankServiceImpl;
import tech.reliab.course.kurmachevem.bank.service.impl.CreditAccountServiceImpl;
import tech.reliab.course.kurmachevem.bank.service.impl.EmployeeServiceImpl;
import tech.reliab.course.kurmachevem.bank.service.impl.PaymentAccountServiceImpl;
import tech.reliab.course.kurmachevem.bank.service.impl.ClientServiceImpl;

import static tech.reliab.course.kurmachevem.bank.utils.Consts.*;

public class Main {
    public static void main(String[] args) throws NotFoundException, NotUniqueIdException {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // Создание сервисов
        BankService bankService = new BankServiceImpl();
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
        EmployeeService employeeService = new EmployeeServiceImpl(bankOfficeService);
        bankOfficeService.setEmployeeService(employeeService);
        AtmService atmService = new AtmServiceImpl(bankOfficeService);
        bankOfficeService.setAtmService(atmService);
        bankService.setBankOfficeService(bankOfficeService);
        ClientService clientService = new ClientServiceImpl(bankService);
        bankService.setClientService(clientService);
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(clientService);
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(clientService);

        // Создадим банки
        bankService.create(new Bank("Iron Bank of Braavos"));
        bankService.create(new Bank("Bank of Casterly Rock"));
        bankService.create(new Bank("Lanisport Bank"));
        bankService.create(new Bank("Bank of Slaver's Bay"));
        bankService.create(new Bank("Bank of Yunkai"));

        // Создание офисов в каждом банке
        List<Bank> banks = bankService.getAllBanks();
        for (Bank bank : banks) {
            for (int i = 1; i <= 3; i++) {
                bankOfficeService.create(new BankOffice(
                        "Office №" + i + " of " + bank.getName(),
                        "Westeros, Red Keep st., " + i,
                        bank,
                        true,
                        true,
                        0,
                        true,
                        true,
                        true,
                        new BigDecimal("0"),
                        new BigDecimal(100 * i)));
            }
        }

        // Добавление сотрудников в каждый офис
        List<BankOffice> offices = bankOfficeService.getAllOffices();
        for (BankOffice office : offices) {
            for (int i = 1; i <= 5; i++) {
                employeeService.create(new Employee(
                        PeopleNames.get(random.nextInt(PeopleNames.size())),
                        LocalDate.of(random.nextInt(1990, 2003), random.nextInt(1, 13),
                                random.nextInt(1, 29)),
                        Employee.Job.getRandom(),
                        office.getBank(),
                        true,
                        office,
                        true,
                        new BigDecimal("300")));
            }
        }

        // Добавление банкоматов в каждый офис
        for (BankOffice office : offices) {
            for (int i = 1; i <= 3; i++) {
                atmService.create(new BankAtm(
                        "Atm " + i,
                        office.getAddress(),
                        BankAtm.Status.WORKING,
                        office.getBank(),
                        office,
                        bankOfficeService.getAllEmployeesByOfficeId(office.getId())
                                .get(random.nextInt(bankOfficeService
                                        .getAllEmployeesByOfficeId(
                                                office.getId())
                                        .size())),
                        true,
                        true,
                        new BigDecimal("1000"),
                        new BigDecimal(random.nextDouble() * 25)));
            }
        }

        // Добавление клиентов в каждый банк
        for (Bank bank : banks) {
            for (int i = 1; i <= 5; i++) {
                clientService.create(
                        new Client(
                                PeopleNames.get(random.nextInt(PeopleNames.size())),
                                LocalDate.of(random.nextInt(200, 300),
                                        random.nextInt(1, 13),
                                        random.nextInt(1, 29)),
                                CompanyNames.get(random.nextInt(CompanyNames.size())),
                                new BigDecimal(random.nextDouble() * 10000),
                                bank,
                                new BigDecimal(random.nextInt(10000))));
            }
        }

        // Добавление платежных счетов каждому клиенту
        List<Client> clients = clientService.getAllClients();
        for (Client client : clients) {
            for (int i = 1; i <= 2; i++) {
                paymentAccountService.create(new PaymentAccount(
                        client,
                        client.getBank(),
                        new BigDecimal(random.nextDouble() * 10000)));
            }
        }

        // Добавление кредитных счетов каждому клиенту
        for (Client client : clients) {
            for (int i = 1; i <= 2; i++) {
                List<BankOffice> bankOffices = bankService
                        .getAllOfficesByBankId(client.getBank().getId());
                BankOffice randomOffice = bankOffices.get(random.nextInt(bankOffices.size()));
                List<Employee> officeEmployees = bankOfficeService
                        .getAllEmployeesByOfficeId(randomOffice.getId());
                Employee randomEmployee = officeEmployees.get(random.nextInt(officeEmployees.size()));

                creditAccountService.create(new CreditAccount(
                        client,
                        client.getBank(),
                        LocalDate.of(2023, 10, 1),
                        LocalDate.of(2026, 10, 1),
                        36,
                        new BigDecimal("2600"),
                        new BigDecimal("2600"),
                        new BigDecimal("100"),
                        client.getBank().getInterestRate(),
                        randomEmployee,
                        clientService.getAllPaymentAccountsByClientId(client.getId())
                                .get(random.nextInt(clientService
                                        .getAllPaymentAccountsByClientId(
                                                client.getId())
                                        .size()))));
            }
        }

        System.out.println("\nLab #3.");

        while (true) {
            try {
                System.out.println("\nPick an action: ");
                System.out.println("b - check bank data by bank id");
                System.out.println("c - check client data by client id");
                System.out.println("t - take credit");
                System.out.println("q - quit program");

                String action = scanner.nextLine();

                if (action.equals("b")) {
                    System.out.println(
                            "Number of banks in the system: "
                                    + bankService.getAllBanks().size());
                    for (Bank bank : bankService.getAllBanks()) {
                        System.out.println("id: " + bank.getId() + " - " + bank.getName());
                    }
                    System.out.println("Enter bank id:");
                    int bankIdToPrint = scanner.nextInt();
                    scanner.nextLine();
                    bankService.printBankData(bankIdToPrint);
                } else if (action.equals("c")) {
                    System.out.println(
                            "Number of clients in the system: "
                                    + clientService.getAllClients().size());
                    for (Client client : clientService.getAllClients()) {
                        System.out.println("id: " + client.getId() + " - " + client.getName());
                    }
                    System.out.println("Enter client id:");
                    int clientIdToPrint = scanner.nextInt();
                    scanner.nextLine();
                    clientService.printClientData(clientIdToPrint, true);
                } else if (action.equals("t")) {
                    System.out.println("What client should take the credit?");
                    for (Client client : clientService.getAllClients()) {
                        System.out.println("id: " + client.getId() + " - " + client.getName());
                    }
                    System.out.println("Enter client id:");
                    int clientId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter total credit amount");
                    BigDecimal amount = new BigDecimal(scanner.nextLine());
                    System.out.println("Enter duration in months:");
                    int months = scanner.nextInt();
                    scanner.nextLine();

                    List<Bank> suitableBanks = bankService.getBanksSuitable(amount, months);
                    System.out.println("List of suitable banks:");
                    for (Bank bank : suitableBanks) {
                        System.out.println("id: " + bank.getId() + " - " + bank.getName());
                    }
                    System.out.println("Enter bank id:");
                    int bankId = scanner.nextInt();
                    scanner.nextLine();
                    Bank bank = bankService.getBankById(bankId);
                    BankOffice bankOffice = bankService.getBankOfficeSuitableInBank(bank, amount)
                            .get(0);
                    Employee employee = bankOfficeService.getSuitableEmployeeInOffice(bankOffice)
                            .get(0);
                    PaymentAccount paymentAccount;
                    // Если платежного счета нет - создадим его
                    try {
                        paymentAccount = clientService.getBestPaymentAccount(clientId);
                    } catch (NoPaymentAccountException e) {
                        paymentAccount = paymentAccountService.create(new PaymentAccount(
                                clientService.getClientById(clientId),
                                clientService.getClientById(clientId).getBank(),
                                new BigDecimal("0")));
                    }
                    CreditAccount creditAccount = creditAccountService.create(new CreditAccount(
                            clientService.getClientById(clientId), bank, LocalDate.now(),
                            months,
                            amount, new BigDecimal("0"), new BigDecimal("0"), employee,
                            paymentAccount));
                    if (bankService.approveCredit(bank, creditAccount, employee)) {
                        System.out.println("Credit was approved");
                        System.out.println("id: " + creditAccount.getId());
                    } else {
                        System.out.println("Credit was not approved");
                    }
                } else if (action.equals("q")) {
                    break;
                } else {
                    System.out.println("Error: unknown action. Please, try again");
                }
            } catch (CreditException e) {
                System.err.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
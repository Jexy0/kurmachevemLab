package tech.reliab.course.kurmachevem.bank.service;

import java.math.BigDecimal;
import java.util.List;

import tech.reliab.course.kurmachevem.bank.entity.Bank;
import tech.reliab.course.kurmachevem.bank.entity.BankOffice;
import tech.reliab.course.kurmachevem.bank.entity.CreditAccount;
import tech.reliab.course.kurmachevem.bank.entity.Employee;
import tech.reliab.course.kurmachevem.bank.entity.User;

public interface BankService {
    // Создание банка
    Bank create(Bank bank);

    // Возвращает банк по его ID
    public Bank getBankById(int bankId);

    // Установка сервиса для управления офисами
    public void setBankOfficeService(BankOfficeService bankOfficeService);

    // Получение всех офисов банка с заданным ID
    public List<BankOffice> getAllOfficesByBankId(int id);

    // Установка сервиса для управления клиентами
    public void setUserService(UserService bankOfficeService);

    // Удаляет банк по его ID
    public boolean deleteBankById(int bankId);

    // Возвращает все банки
    public List<Bank> getAllBanks();

    // Вывод данных о банке с заданным ID
    public void printBankData(int bankId);

    // Добавление офиса
    public boolean addOffice(int bankId, BankOffice bankOffice);

    // Удаление офиса
    public boolean removeOffice(int bankId, BankOffice bankOffice);

    // Добавление сотрудника
    public boolean addEmployee(Bank bank, Employee employee);

    // Удаление сотрудника
    public boolean removeEmployee(Bank bank, Employee employee);

    // Добавление клиента
    public boolean addUser(int id, User client);

    // Удаление клиента
    boolean removeUser(Bank bank, User user);

    // Расчет процентной ставки банка
    public BigDecimal calculateInterestRate(Bank bank);

    // Внести amount денег в банк
    public boolean depositMoney(int id, BigDecimal amount);

    // Вывести amount денег из банка
    public boolean withdrawMoney(int id, BigDecimal amount);

    // Оформление заявки на кредит
    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee);
}
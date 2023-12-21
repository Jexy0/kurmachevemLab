package tech.reliab.course.kurmachevem.bank.service;

import tech.reliab.course.kurmachevem.bank.entity.BankOffice;
import tech.reliab.course.kurmachevem.bank.entity.Employee;

public interface EmployeeService {
    Employee create(Employee employee);

    boolean transferEmployee(Employee employee, BankOffice bankOffice);
}
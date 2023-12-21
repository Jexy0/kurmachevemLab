package tech.reliab.course.kurmachevem.bank.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.kurmachevem.bank.entity.BankOffice;
import tech.reliab.course.kurmachevem.bank.entity.Employee;
import tech.reliab.course.kurmachevem.bank.service.BankOfficeService;
import tech.reliab.course.kurmachevem.bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
    private final Map<Integer, Employee> employeesTable = new HashMap<>();
    private final BankOfficeService bankOfficeService;

    public EmployeeServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public Employee create(Employee employee) {
        if (employee == null) {
            return null;
        }

        Employee newEmployee = new Employee(employee);
        employeesTable.put(newEmployee.getId(), newEmployee);
        bankOfficeService.addEmployee(newEmployee.getBankOffice().getId(), newEmployee);

        if (employee.getSalary().signum() < 0) {
            System.err.println("Error: Employee - salary must be non-negative");
            return null;
        }

        // TODO: Добавить механизм проверки на имеющийся офис и добавление в офис

        return newEmployee;
    }

    @Override
    public boolean transferEmployee(Employee employee, BankOffice bankOffice) {
        // TODO: Добавить механизм перевода сотрудника в новый офис

        return true;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<Employee>(employeesTable.values());
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = employeesTable.get(id);
        if (employee == null) {
            System.err.println("Employee with id " + id + " is not found");
        }
        return employee;
    }
}
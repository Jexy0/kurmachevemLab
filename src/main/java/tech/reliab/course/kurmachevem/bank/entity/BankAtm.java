package tech.reliab.course.kurmachevem.bank.entity;

import java.math.BigDecimal;

public class BankAtm {
    public enum WorkingStatus {
        NOT_WORKING,
        WORKING
    }

    public enum MoneyStatus {
        NO_MONEY,
        HAS_MONEY
    }

    private static int currentId;
    private int id;
    private String name;
    private String address;
    private WorkingStatus workingStatus;
    private MoneyStatus moneyStatus;
    private Bank bank;
    private BankOffice bankOffice;
    private Employee employee;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal maintenanceCost;

    public BankAtm(BankAtm bankAtm) {
        this.id = bankAtm.id;
        this.name = bankAtm.name;
        this.address = bankAtm.address;
        this.workingStatus = bankAtm.workingStatus;
        this.moneyStatus = bankAtm.moneyStatus;
        this.bank = new Bank(bankAtm.bank);
        this.bankOffice = new BankOffice(bankAtm.bankOffice);
        this.employee = new Employee(bankAtm.employee);
        this.isCashWithdrawalAvailable = bankAtm.isCashWithdrawalAvailable;
        this.isCashDepositAvailable = bankAtm.isCashDepositAvailable;
        this.totalMoney = bankAtm.totalMoney;
        this.maintenanceCost = bankAtm.maintenanceCost;
    }

    private void initId() {
        id = currentId++;
    }

    public BankAtm() {
        initId();
        initWithDefaults();
    }

    public BankAtm(String name, String address) {
        initId();
        initWithDefaults();
        this.name = name;
        this.address = address;
    }

    public BankAtm(String name, String address, WorkingStatus workingStatus, MoneyStatus moneyStatus, Bank bank, BankOffice bankOffice,
                   Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, BigDecimal totalMoney,
                   BigDecimal maintenanceCost) {
        initId();
        initWithDefaults();
        this.name = name;
        this.address = address;
        this.workingStatus = workingStatus;
        this.moneyStatus = moneyStatus;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    public BankAtm(int id, String name, String address, WorkingStatus workingStatus, MoneyStatus moneyStatus, Bank bank, BankOffice bankOffice,
                   Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, BigDecimal totalMoney,
                   BigDecimal maintenanceCost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.workingStatus = workingStatus;
        this.moneyStatus = moneyStatus;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    @Override
    public String toString() {
        return "BankAtm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", workingStatus=" + workingStatus +
                ", moneyStatus=" + moneyStatus +
                ", bank=" + bank +
                ", bankOffice=" + bankOffice +
                ", employee=" + employee +
                ", isCashWithdrawalAvailable=" + isCashWithdrawalAvailable +
                ", isCashDepositAvailable=" + isCashDepositAvailable +
                ", totalMoney=" + totalMoney +
                ", maintenanceCost=" + maintenanceCost +
                '}';
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public WorkingStatus getWorkingStatus() {
        return workingStatus;
    }

    public MoneyStatus getMoneyStatus() {
        return moneyStatus;
    }

    public void setWorkingStatus(WorkingStatus workingStatus) {
        this.workingStatus = workingStatus;
    }

    public void setMoneyStatus(MoneyStatus moneyStatus) {
        this.moneyStatus = moneyStatus;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BankOffice getBankOffice() {
        return this.bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isIsCashWithdrawalAvailable() {
        return this.isCashWithdrawalAvailable;
    }

    public boolean getIsCashWithdrawalAvailable() {
        return this.isCashWithdrawalAvailable;
    }

    public void setIsCashWithdrawalAvailable(boolean isCashWithdrawalAvailable) {
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
    }

    public boolean isIsCashDepositAvailable() {
        return this.isCashDepositAvailable;
    }

    public boolean getIsCashDepositAvailable() {
        return this.isCashDepositAvailable;
    }

    public void setIsCashDepositAvailable(boolean isCashDepositAvailable) {
        this.isCashDepositAvailable = isCashDepositAvailable;
    }

    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getMaintenanceCost() {
        return this.maintenanceCost;
    }

    public void setMaintenanceCost(BigDecimal maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    private void initWithDefaults() {
        name = "No name";
        address = "No address";
        workingStatus = WorkingStatus.NOT_WORKING;
        moneyStatus = MoneyStatus.NO_MONEY;
        bank = null;
        bankOffice = null;
        employee = null;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = new BigDecimal("0");
        maintenanceCost = new BigDecimal("0");
    }

}
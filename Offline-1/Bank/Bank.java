package Bank;

import Allexceptions.creation_exceptions;
import Allexceptions.deposit_exceptions;
import Allexceptions.query_exceptions;
import account.Accounts;
import account.Fixed_deposit;
import account.Savings;
import account.Student;
import employee.Cashier;
import employee.Employees;
import employee.Managing_Director;
import employee.Officer;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static Bank instance = null;
    private double internalFund;
    private int clock;
    private List<Accounts> account;
    private List<Employees> employees;

   private Bank() {
        this.internalFund = 1000000;
        this.clock = 0;
        this.account = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public void instantiation() {
        employees.add(new Managing_Director("MD"));
        employees.add(new Officer("S1"));
        employees.add(new Officer("S2"));
        employees.add(new Cashier("C1"));
        employees.add(new Cashier("C2"));
        employees.add(new Cashier("C3"));
        employees.add(new Cashier("C4"));
        employees.add(new Cashier("C5"));
    }

    public void incrementyear() throws deposit_exceptions {
        addinterestrate();
        deduct_loan_req();
        deduct_service_charge();
        clock++;
    }

    private void addinterestrate() throws deposit_exceptions {
        for (Accounts account : account)
            account.add_interest_rate();
    }

    private void deduct_loan_req() {
        for (Accounts account : account)
            account.deductloanreq();
    }

    private void deduct_service_charge() throws deposit_exceptions {

        for (Accounts account : account)
            account.deductServiceCharge();
    }

    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public void setInternalFund(double internalFund) {
        this.internalFund = internalFund;
    }

    public double getInternalFund() {
        return internalFund;
    }

    public int getClock() {
        return clock;
    }

    public List<Accounts> getAccounts() {
        return account;
    }

    public List<Employees> getEmployees() {
        return employees;
    }


    public void addAccount(String type, String name, double amount) throws creation_exceptions {
        String acc_type = type;
        System.out.println(acc_type);
        if (acc_type.trim().toLowerCase().equals("student"))
            account.add(new Student(name, amount));
        else if (acc_type.trim().toLowerCase().equals("savings"))
            account.add(new Savings(name, amount));
        else if (acc_type.trim().toLowerCase().equals("fixed deposit"))
            account.add(new Fixed_deposit(name, amount));
        else throw new creation_exceptions("Invalid Account Type");
    }

    public Accounts findByNameA(String name) throws query_exceptions {
        Accounts found = null;
        for (Accounts account : account) {
            if (account.getName().trim().toLowerCase().equals(name.trim().toLowerCase())) {
                found = account;
                break;
            }
        }
        if (found == null) throw new query_exceptions("User doesn't exist");
        return found;
    }

    public Employees findByNameE(String name) throws query_exceptions {
        Employees found = null;
        for (Employees employee : employees) {
            if (employee.getName().trim().toLowerCase().equals(name.trim().toLowerCase())) {
                found = employee;
                break;
            }
        }
        if (found == null) throw new query_exceptions("Employee doesn't exist");
        return found;
    }

    public boolean samename(String name) {
        boolean exits = false;
        for (Accounts account : account)
            if (account.getName().trim().toLowerCase().equals(name.trim().toLowerCase())) {
                exits = true;
                break;
            }
        return exits;
    }

    public boolean LoanApprovalPending() {
        boolean approved = false;
        for (Accounts account : account)
            if (account.getLoanreq() > 0) {
                approved = true;
                break;
            }
        return approved;


    }
}
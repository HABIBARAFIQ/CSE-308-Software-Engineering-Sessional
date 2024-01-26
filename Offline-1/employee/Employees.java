package employee;

import Allexceptions.query_exceptions;
import Bank.Bank;
import account.Accounts;

import java.io.BufferedWriter;

public class Employees {
    private String name;
    public Employees() {
    }
    public Employees(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public double Lookup(String name)throws query_exceptions
    {
        if(!Bank.getInstance().samename(name))
            throw new query_exceptions("User not found");
        Accounts account= Bank.getInstance().findByNameA(name);
        return account.getDeposit();
    }

    public void Approve_Loan(String name) throws query_exceptions{
        if(!Bank.getInstance().samename(name))
            throw new query_exceptions("User not found");
        Accounts account= Bank.getInstance().findByNameA(name);
        if(account.getLoanreq()>0){
            account.addLoan();
            account.renew();
        }else throw new query_exceptions("Not requested by the user");
    }
    public void Change_Interest_Rate(String acc_type, double pct)throws query_exceptions{
        throw new query_exceptions("You don’t have permission for this operation");
    }
    public double checkInternalFunds() throws query_exceptions {
        throw new query_exceptions("You don’t have permission for this operation");
    }

    }

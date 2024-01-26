package account;

import Allexceptions.*;
import Bank.Bank;

public class Fixed_deposit extends Accounts{
    public Fixed_deposit(String name, double deposit) throws creation_exceptions {
        super(name, deposit, 0);
        if(deposit< 100000)
            throw new creation_exceptions("Initial fixed deposit amount must be at least 100,000$");


    }

    @Override
    public void setDeposit(double deposit) throws deposit_exceptions {
        if(deposit<50000)
            throw new deposit_exceptions("The deposit amount must not be less than 50,000$");
        super.setDeposit(deposit);

    }

    public void withdraw(double amount) throws withdrawal_exceptions {
        if(Bank.getInstance().getClock()<1)
            throw new withdrawal_exceptions("Cannot withdraw from a fixed deposit account before reaching maturity of 1 year");
        super.withdraw(amount);
    }
    public void loan_req(double req_loan) throws loan_exceptions {
        if(req_loan+getLoan()>100000)
            throw new loan_exceptions("Loan request exceeds 100000$");
        super.loan_req(req_loan);
    }
    public double Query_Deposit()throws query_exceptions {
        return getDeposit();
    }
    public double Interest(){
        return all_loan_and_interest_pct.interest_pct_fixed_deposit;
    }
    public void deductServiceCharge() throws deposit_exceptions {
        if(getDeposit()<500)
            setDeposit(0);
        else setDeposit(getDeposit()-500);
    }
    public void add_interest_rate() throws deposit_exceptions {
        setDeposit(getDeposit()+getDeposit()*Interest());
    }
    }

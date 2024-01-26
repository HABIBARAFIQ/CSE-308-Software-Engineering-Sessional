package account;

import Allexceptions.creation_exceptions;
import Allexceptions.deposit_exceptions;
import Allexceptions.loan_exceptions;
import Allexceptions.withdrawal_exceptions;
import Bank.Bank;

public class Savings extends Accounts{
    public Savings(String name, double deposit) throws creation_exceptions {
        super(name, deposit, 0);
    }
    public void setDeposit(double deposit) throws deposit_exceptions {
        super.setDeposit(deposit);
    }
    @Override
    public void withdraw(double amount) throws withdrawal_exceptions {
        if(getDeposit()-1000<0)
            throw new withdrawal_exceptions("Withdrawal results in a deposit of less than $1,000 in a savings account.");
        super.withdraw(amount);
    }
    public void loan_req(double req_loan) throws loan_exceptions {
        if(req_loan+getLoan()>10000)
            throw new loan_exceptions("Loan request exceeds 10000$");
        super.loan_req(req_loan);
    }
    public double Interest(){
        return all_loan_and_interest_pct.interest_pct_savings;
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

package account;

import Allexceptions.creation_exceptions;
import Allexceptions.deposit_exceptions;
import Allexceptions.loan_exceptions;
import Allexceptions.withdrawal_exceptions;
import Bank.Bank;

import static account.all_loan_and_interest_pct.interst_pct_loan;

public class Accounts {
    private String name;
    private double deposit;
    private double loan;
    private double Loanreq;

    public Accounts() {

    }
    public Accounts(String name,double deposit,double loan) throws creation_exceptions {
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            throw new creation_exceptions("Name can't be empty");
        }
        //GET INSTANCE OF NAME
        if(Bank.getInstance().samename(name))
        {
            throw new creation_exceptions("Account already exists with this name");
        }
        this.name=name;
        this.deposit=deposit;
        this.loan=loan;
        this.Loanreq=0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeposit(double deposit) throws deposit_exceptions {
        this.deposit = deposit;
    }
    public void setdepositAndInternalFund(double deposit) throws deposit_exceptions {
        this.deposit+=deposit;
        Bank.getInstance().setInternalFund(Bank.getInstance().getInternalFund()+deposit);
    }
    public void setLoan(double loan) {
        this.loan = loan;
    }

    public String getName() {
        return name;
    }

    public double getDeposit() {
        return deposit;
    }

    public double getLoan() {
        return loan;
    }

    public double getLoanreq() {
        return Loanreq;
    }

    public void withdraw(double amount) throws withdrawal_exceptions {
        if(amount>deposit)
            throw new withdrawal_exceptions("You cannot withdraw more than your deposit amount");
        deposit-=amount;
        Bank.getInstance().setInternalFund(Bank.getInstance().getInternalFund()-amount);
    }

    public void loan_req(double req_loan) throws loan_exceptions {
        if(Loanreq>0)
            throw new loan_exceptions("Loan already requested for this account");
        Loanreq=req_loan;
    }
    public double Interest(){
        return 0;
    }
    public void addLoan() {
        loan+=Loanreq;
        deposit+=Loanreq;
    }

    public void renew() {
        Loanreq=0;
    }

    public void deductServiceCharge() throws deposit_exceptions {

    }

    public void deductloanreq() {
        if(loan*interst_pct_loan>deposit)
            deposit=0;
        else deposit-=loan*interst_pct_loan;

    }

    public void add_interest_rate() throws deposit_exceptions {

    }
}

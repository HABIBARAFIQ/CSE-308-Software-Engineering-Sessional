package account;

import Allexceptions.creation_exceptions;
import Allexceptions.deposit_exceptions;
import Allexceptions.loan_exceptions;
import Allexceptions.withdrawal_exceptions;

public class Student extends Accounts{
    public Student(String name, double deposit) throws creation_exceptions {
        super(name, deposit, 0);
    }
    public void setDeposit(double deposit) throws deposit_exceptions {
        super.setDeposit(deposit);}
    @Override
    public void withdraw(double amount) throws withdrawal_exceptions {
        if (amount > 10000) {
            throw new withdrawal_exceptions("Cannot withdraw more than $10,000 from a student account.");
        }
        super.withdraw(amount);
    }
    public void loan_req(double req_loan) throws loan_exceptions {
        if(req_loan+getLoan()>1000)
            throw new loan_exceptions("Loan request exceeds 1000$");
        super.loan_req(req_loan);
    }
    public double Interest(){
        return all_loan_and_interest_pct.interest_pct_student;
    }
    public void add_interest_rate() throws deposit_exceptions {
        setDeposit(getDeposit()+getDeposit()*Interest());
    }
}

package employee;

import Allexceptions.query_exceptions;
import Bank.Bank;
import account.all_loan_and_interest_pct;

public class Managing_Director extends Employees{
    public Managing_Director() {
    }

    public Managing_Director(String name) {
        super(name);
    }
    public void Change_Interest_Rate(String acc_type,double pct)throws query_exceptions{
       if(acc_type.trim().toLowerCase().equals("student"))
           all_loan_and_interest_pct.interest_pct_student=pct/100;
       else if(acc_type.trim().toLowerCase().equals("savings"))
           all_loan_and_interest_pct.interest_pct_savings=pct/100;
       else if(acc_type.trim().toLowerCase().equals("fixed deposit"))
           all_loan_and_interest_pct.interest_pct_fixed_deposit=pct/100;
    }
    public double checkInternalFunds() throws query_exceptions {
      return Bank.getInstance().getInternalFund();
    }
}

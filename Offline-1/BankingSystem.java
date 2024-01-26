import Allexceptions.*;
import Bank.Bank;
import account.Accounts;
import employee.Employees;
import employee.Managing_Director;
import employee.Officer;

import java.io.*;

public class BankingSystem {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        try (
            FileWriter fileWriter = new FileWriter(outputFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));)
        {
            String line;
            Accounts accounts = null;
            Employees employees = null;
            Bank.getInstance().instantiation();
            bufferedWriter.write("Bank Created; MD, S1, S2, C1, C2, C3, C4, C5 created");
            bufferedWriter.newLine();
                       while ((line = reader.readLine()) != null) {
                                try {
                                        String[] tokens = line.split(" ");
                    switch (tokens[0]) {
                        case "Create":
                            try {
                                Bank.getInstance().addAccount(tokens[2], tokens[1], Double.parseDouble(tokens[3]));
                                accounts = Bank.getInstance().findByNameA(tokens[1]);
                                bufferedWriter.write(tokens[2] + " account for " + tokens[1] + " Created; initial balance " + Double.parseDouble(tokens[3]) + "$\n");
                            } catch (creation_exceptions e) {
                                bufferedWriter.write(e.getMessage());
                                bufferedWriter.newLine();
                            } catch (query_exceptions e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case "Deposit":
                            try {
                                accounts.setdepositAndInternalFund(Double.parseDouble(tokens[1]));
                                bufferedWriter.write(Double.parseDouble(tokens[1]) + "$ deposited; current balance " + accounts.getDeposit() + "$\n");
                            } catch (deposit_exceptions e) {
                                bufferedWriter.write("Invalid transaction; current balance " + accounts.getDeposit() + "$\n");
                            }
                            break;
                        case "Withdraw":
                            try {
                                accounts.withdraw(Double.parseDouble(tokens[1]));
                                bufferedWriter.write(Double.parseDouble(tokens[1]) + "$ withdrawn; current balance " + accounts.getDeposit() + "$\n");
                            } catch (withdrawal_exceptions e) {
                                bufferedWriter.write("Invalid transaction; current balance " + accounts.getDeposit() + "$\n");
                            }
                            break;
                        case "Query":
                            if(accounts.getLoan()>0) bufferedWriter.write("Current balance "+accounts.getDeposit()+"$, loan "+accounts.getLoan()+"$");
                            else bufferedWriter.write("Current balance "+accounts.getDeposit());
                            bufferedWriter.newLine();
                            break;
                        case "Request":
                    try {
                        accounts.loan_req(Double.parseDouble(tokens[1]));
                        bufferedWriter.write("Loan request successful, sent for approval\n");
                    } catch (loan_exceptions e) {
                        bufferedWriter.write(e.getMessage());
                        bufferedWriter.newLine();
                    }
                            break;
                        case "Close":
                    if(accounts!=null)
                        bufferedWriter.write("Transaction Closed for "+accounts.getName()+"\n");
                    else if(employees!=null)
                        bufferedWriter.write("Transaction for "+employees.getName()+" Closed\n");
                    accounts=null;
                    employees=null;
                            break;
                        case "Open":
                    try {
                        accounts=Bank.getInstance().findByNameA(tokens[1]);
                        bufferedWriter.write("Welcome back, "+tokens[1]+"\n");}
                    catch (query_exceptions e) {

                    }
                    if(accounts==null){
                        try {
                            employees=Bank.getInstance().findByNameE(tokens[1]);
                            bufferedWriter.write(tokens[1]+" active");
                            if((Bank.getInstance().LoanApprovalPending()&&(employees instanceof Managing_Director||employees instanceof Officer)))
                                bufferedWriter.write(", there are loan approvals pending"+"\n");
                            else bufferedWriter.write("");
                        } catch (query_exceptions e) {
                            bufferedWriter.write(e.getMessage());
                            bufferedWriter.newLine();
                        }
                    }
                            break;
                        case "Approve":
                    try {
                        for(Accounts account : Bank.getInstance().getAccounts())
                            if(account.getLoanreq()>0) {
                                employees.Approve_Loan(account.getName());
                                bufferedWriter.write("Loan for "+account.getName()+" approved\n");
                            }
                    } catch (query_exceptions e) {
                        bufferedWriter.write(e.getMessage());
                        bufferedWriter.newLine();
                    }
                            break;
                        case "Change":
                    try {
                        employees.Change_Interest_Rate(tokens[1],Double.parseDouble(tokens[2]));
                    } catch (query_exceptions e) {
                        bufferedWriter.write(e.getMessage());
                        bufferedWriter.newLine();
                    }
                            break;
                        case "Lookup":
                    try {
                        double deposit=employees.Lookup(tokens[1]);
                        bufferedWriter.write(tokens[1]+"'s current balance "+deposit+"$\n");
                    } catch (query_exceptions e) {
                        bufferedWriter.write(e.getMessage());
                        bufferedWriter.newLine();
                    }
                            break;
                        case "See":
                            try {
                            double fund=employees.checkInternalFunds();
                             bufferedWriter.write("Internal fund is $"+fund+"\n");
                            } catch (query_exceptions e) {
                              bufferedWriter.write(e.getMessage());
                              bufferedWriter.newLine();
                             }
                            break;
                        case "INC":
                           Bank.getInstance().incrementyear();
                           bufferedWriter.write(Bank.getInstance().getClock()+" year passed");
                           bufferedWriter.newLine();
                           break;
                        default:
                            System.out.println("Invalid command: " + line);
                    }
                } catch (Exception e) {
                    System.out.println("Error processing command '" + line + "': " + e.getMessage());
                }
            }

            System.out.println("Data written to " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

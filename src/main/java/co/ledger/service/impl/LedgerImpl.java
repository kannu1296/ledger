package co.ledger.service.impl;

import co.ledger.pojo.LoanInfo;
import co.ledger.service.Ledger;

import java.util.HashMap;
import java.util.Map;

/**
 * class to implement ledger activities
 */
public class LedgerImpl  implements Ledger {

    private Map<String, LoanInfo> loanInfoMap;

    public LedgerImpl(){
        loanInfoMap = new HashMap<>();
    }

    /**
     * This method is used to store loan information
     * @param bankName
     * @param borrowerName
     * @param principal
     * @param noOfYears
     * @param ratOfInterest
     */
    @Override
    public void loan(String bankName, String borrowerName, int principal, int noOfYears, int ratOfInterest) {
        String loanInfoMapKey = bankName + borrowerName;
        int totalAmount = this.calculateTotalAmount(principal, noOfYears, ratOfInterest);
        int noOfEmis = this.calculateNoOfEmis(noOfYears);
        int emiAmount = this.calculateEmiAmount(totalAmount, noOfEmis);

        loanInfoMap.put(loanInfoMapKey, new LoanInfo(totalAmount, noOfEmis, emiAmount));

        System.out.println("Total Amount: " + totalAmount + " No Of Emis: "+ noOfEmis +
                " Emi Amount: "+ emiAmount + " Loan Info -> " + loanInfoMap);
    }

    @Override
    public void payment(String bankName, String borrowerName, int lumpSumAmount, int emiNumber) {

    }

    @Override
    public int balance(String bankName, String borrowerName, int emiNumber) {
        return 0;

    }

    private int calculateTotalAmount(int principal, int noOfYears, int ratOfInterest){
        int interestAmount = this.calculateInterestAmount(principal, noOfYears, ratOfInterest);

        return principal + interestAmount;
    }

    private int calculateInterestAmount(int principal, int noOfYears, int rateOfInterest){
        return (principal * noOfYears * rateOfInterest)/100;
    }

    private int calculateNoOfEmis(int noOfYears){
        return noOfYears*12;
    }

    private int calculateEmiAmount(int totalAmount, int noOfYears){
        return (int)Math.ceil((double) totalAmount / noOfYears);
    }
}

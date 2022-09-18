package co.ledger.service.impl;

import co.ledger.dto.requestdto.BalanceRequestDto;
import co.ledger.dto.requestdto.LoanRequestDto;
import co.ledger.dto.requestdto.PaymentRequestDto;
import co.ledger.dto.responsedto.BalanceResponseDto;
import co.ledger.pojo.LoanInfo;
import co.ledger.pojo.LumpSumInfo;
import co.ledger.service.Ledger;

import java.util.HashMap;
import java.util.Map;

/**
 * class to implement ledger activities
 */
public class LedgerImpl implements Ledger {

    private Map<String, LoanInfo> loanInfoMap;
    private Map<String, LumpSumInfo> lumpSumInfoMap;

    public LedgerImpl(){
        loanInfoMap = new HashMap<>();
        lumpSumInfoMap = new HashMap<>();
    }

    /**
     * This method is used to store loan information
     */
    @Override
    public void loan(LoanRequestDto loanRequestDto) {
        String loanInfoMapKey = loanRequestDto.getBankInfoDto().getBankName() + loanRequestDto.getBankInfoDto().getBorrowerName();
        int totalAmount = this.calculateTotalAmount(loanRequestDto.getPrincipal(),
                loanRequestDto.getNoOfYears(), loanRequestDto.getRateOfInterest());

        int noOfEmis = this.calculateNoOfEmis(loanRequestDto.getNoOfYears());
        int emiAmount = this.calculateEmiAmount(totalAmount, noOfEmis);

        loanInfoMap.put(loanInfoMapKey, new LoanInfo(totalAmount, noOfEmis, emiAmount));

//        System.out.println("Total Amount: " + totalAmount + " No Of Emis: "+ noOfEmis +
//                " Emi Amount: "+ emiAmount + " Loan Info -> " + loanInfoMap);
    }

    /**
     * This Method is used to store lump sum payment information
     */
    @Override
    public void payment(PaymentRequestDto paymentRequestDto){
        String lumpSumMapKey = paymentRequestDto.getBankInfoDto().getBankName() + paymentRequestDto.getBankInfoDto().getBorrowerName();

        lumpSumInfoMap.put(lumpSumMapKey, new LumpSumInfo(paymentRequestDto.getLumpSumAmount(), paymentRequestDto.getEmiNumber()));

//        System.out.println("Emi Number: " + emiNumber + " Lump Sum Amount: "+ lumpSumAmount +
//                " Lump Sum Amount Map -> " + lumpSumInfoMap);
    }

    /**
     * This Method calculate balance info of user
     */
    @Override
    public BalanceResponseDto balance(BalanceRequestDto balanceRequestDto){
        int remainingNoOfEmis;
        int totalAmountPaid;

        String key = balanceRequestDto.getBankInfoDto().getBankName() + balanceRequestDto.getBankInfoDto().getBorrowerName();
        int emiNumber = balanceRequestDto.getEmiNumber();

        if(!loanInfoMap.containsKey(key)){
            System.out.println("Not a valid Emi Account");
            return null;
        }

        LoanInfo loanInfo = loanInfoMap.get(key);

        if(lumpSumInfoMap.containsKey(key) && lumpSumInfoMap.get(key).getEmiNumber() <= emiNumber){
            int lumpSumAmount = lumpSumInfoMap.get(key).getLumpSumAmount();
            int emiAmount = loanInfo.getEmiAmount();

            totalAmountPaid = emiAmount * emiNumber + lumpSumAmount;

            int totalAmount = loanInfo.getTotalAmount();

            remainingNoOfEmis = (int)Math.ceil((double)(totalAmount - totalAmountPaid) / emiAmount);
        } else {
            int emiAmount = loanInfo.getEmiAmount();

            totalAmountPaid = emiAmount * emiNumber;
            remainingNoOfEmis = loanInfo.getNoOfEmis() - emiNumber;
        }

        return BalanceResponseDto.builder().totalAmountPaid(totalAmountPaid).
                remainingNoOfEmis(remainingNoOfEmis).
                build();
    }


    private int calculateTotalAmount(int principal, int noOfYears, int ratOfInterest){
        int interestAmount = this.calculateInterestAmount(principal, noOfYears, ratOfInterest);

        return principal + interestAmount;
    }

    private int calculateInterestAmount(int principal, int noOfYears, int rateOfInterest){
        return (principal * noOfYears * rateOfInterest)/100;
    }

    private int calculateEmiAmount(int totalAmount, int noOfEmis){
        return (int)Math.ceil((double) totalAmount / noOfEmis);
    }

    private int calculateNoOfEmis(int noOfYears){
        return noOfYears*12;
    }
}

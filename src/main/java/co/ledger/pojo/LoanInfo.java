package co.ledger.pojo;

public class LoanInfo {
    private int totalAmount;
    private int noOfEmis;
    private int emiAmount;

    public LoanInfo(int totalAmount, int noOfEmis, int emiAmount){
        this.emiAmount = emiAmount;
        this.totalAmount = totalAmount;
        this.noOfEmis = noOfEmis;
    }

    @Override
    public String toString() {
        return totalAmount+","+noOfEmis+","+emiAmount;
    }
}

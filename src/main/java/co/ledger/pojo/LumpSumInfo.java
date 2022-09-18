package co.ledger.pojo;

public class LumpSumInfo {
    private int lumpSumAmount;
    private int emiNumber;

    public LumpSumInfo(int lumpSumAmount, int emiNumber) {
        this.lumpSumAmount = lumpSumAmount;
        this.emiNumber = emiNumber;
    }

    public int getLumpSumAmount() {
        return lumpSumAmount;
    }

    public int getEmiNumber() {
        return emiNumber;
    }

    @Override
    public String toString() {
        return lumpSumAmount +","+ emiNumber;
    }
}

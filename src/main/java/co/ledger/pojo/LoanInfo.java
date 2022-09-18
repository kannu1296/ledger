package co.ledger.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class LoanInfo {
    private int totalAmount;
    private int noOfEmis;
    private int emiAmount;
}

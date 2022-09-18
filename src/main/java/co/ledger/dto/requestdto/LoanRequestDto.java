package co.ledger.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class LoanRequestDto {
    private BankInfoDto bankInfoDto;
    private int principal;
    private int noOfYears;
    private int rateOfInterest;
}

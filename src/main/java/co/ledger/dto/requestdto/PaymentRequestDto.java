package co.ledger.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class PaymentRequestDto {
    private BankInfoDto bankInfoDto;
    private int lumpSumAmount;
    private int emiNumber;
}

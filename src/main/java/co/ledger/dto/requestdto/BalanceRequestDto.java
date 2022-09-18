package co.ledger.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class BalanceRequestDto {
    private BankInfoDto bankInfoDto;
    private int emiNumber;
}

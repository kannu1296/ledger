package co.ledger.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BankInfoDto {
    private String bankName;
    private String borrowerName;
}

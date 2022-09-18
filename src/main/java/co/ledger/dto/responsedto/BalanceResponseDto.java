package co.ledger.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class BalanceResponseDto {
    private int totalAmountPaid;
    private int remainingNoOfEmis;
}

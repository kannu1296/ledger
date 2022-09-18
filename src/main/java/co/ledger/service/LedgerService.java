package co.ledger.service;

import co.ledger.dto.requestdto.BalanceRequestDto;
import co.ledger.dto.requestdto.LoanRequestDto;
import co.ledger.dto.requestdto.PaymentRequestDto;
import co.ledger.dto.responsedto.BalanceResponseDto;

/**
 * Interface to do Ledger activities
 */
public interface LedgerService {
    void loan(LoanRequestDto loanRequestDto);
    void payment(PaymentRequestDto paymentRequestDto);
    BalanceResponseDto balance(BalanceRequestDto balanceRequestDto);
}

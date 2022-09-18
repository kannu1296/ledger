package co.ledger.service

import co.ledger.dto.requestdto.BalanceRequestDto
import co.ledger.dto.requestdto.BankInfoDto
import co.ledger.dto.requestdto.LoanRequestDto
import co.ledger.dto.requestdto.PaymentRequestDto
import co.ledger.dto.responsedto.BalanceResponseDto
import co.ledger.service.impl.LedgerServiceImpl
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class LedgerServiceImplSpec extends Specification{
    def "test ledger impl loan mathod"(){

        given: "Ledger Object"
        LedgerService ledger = new LedgerServiceImpl()

        when: "Loan Method is called"
        ledger.loan(buildLoanRequestDto("IDIDI", "Dale", 10000, 5, 4))

        then: "Loan Info Should be created successfully"
        0*ledger.loan(_,_,_,_,_)
    }

    def "test ledger impl payment mathod"(){

        given: "Ledger Object"
        LedgerService ledger = new LedgerServiceImpl()

        when: "payment Method is called"
        ledger.payment(buildPaymentRequestDto("IDIDI", "Dale", 1000, 5))

        then: "Lump Sum Info Should be created successfully"
        0*ledger.loan(_,_,_,_,_)
    }

    @Shared
    BalanceResponseDto balanceResponseDto1 = BalanceResponseDto.builder().
            totalAmountPaid(1326).
            remainingNoOfEmis(9).
            build()
    @Shared
    BalanceResponseDto balanceResponseDto2 = BalanceResponseDto.builder().
            totalAmountPaid(3652).
            remainingNoOfEmis(4).
            build()
    @Shared
    BalanceResponseDto balanceResponseDto3 = BalanceResponseDto.builder().
            totalAmountPaid(15856).
            remainingNoOfEmis(3).
            build()
    @Shared
    BalanceResponseDto balanceResponseDto4 = BalanceResponseDto.builder().
            totalAmountPaid(9044).
            remainingNoOfEmis(10).
            build()
    @Shared
    BalanceResponseDto balanceResponseDto5 = BalanceResponseDto.builder().
            totalAmountPaid(3210).
            remainingNoOfEmis(5).
            build()

    @Shared
    BalanceRequestDto balanceRequestDto1 = buildBalanceRequestDto("IDIDI", "Dale", 3)
    @Shared
    BalanceRequestDto balanceRequestDto2 = buildBalanceRequestDto("IDIDI", "Dale", 6)
    @Shared
    BalanceRequestDto balanceRequestDto3 = buildBalanceRequestDto("IDIDI", "Dale", 5)
    @Shared
    BalanceRequestDto balanceRequestDto4 = buildBalanceRequestDto("UON", "Shelly", 12)
    @Shared
    BalanceRequestDto balanceRequestDto5 = buildBalanceRequestDto("MBI", "Harry", 12)
    @Shared
    BalanceRequestDto balanceRequestDto6 = buildBalanceRequestDto("MBB", "Har", 12)


    @Unroll
    def "test ledger impl balance method"() {
        given: "Ledger object, loanInfo, & lumpSumMap"
        LedgerService ledger = new LedgerServiceImpl()

        ledger.loan(buildLoanRequestDto("IDIDI", "Dale", 5000, 1, 6))
        ledger.loan(buildLoanRequestDto("MBI", "Harry", 10000, 3, 7))
        ledger.loan(buildLoanRequestDto("UON", "Shelly", 15000, 2, 9))

        ledger.payment(buildPaymentRequestDto("IDIDI", "Dale", 1000, 5))
        ledger.payment(buildPaymentRequestDto("MBI", "Harry", 5000, 10))
        ledger.payment(buildPaymentRequestDto("UON", "Shelly", 7000, 12))



        when: "balance method is called with diiferent inputs"
        def response = ledger.balance(balanceRequestDto)

        then: "balance response should be equivalent to expected response"
        response == expectedResponse

        where:
        balanceRequestDto  | expectedResponse
        balanceRequestDto1 | balanceResponseDto1
        balanceRequestDto2 | balanceResponseDto2
        balanceRequestDto3 | balanceResponseDto5
        balanceRequestDto4 | balanceResponseDto3
        balanceRequestDto5 | balanceResponseDto4
        balanceRequestDto6 | null
    }


    private LoanRequestDto buildLoanRequestDto(String bankName,
                                               String borrowerName,
                                               int principal,
                                               int noOfYears,
                                               int rateOfInterest){
        BankInfoDto bankInfoDto = BankInfoDto.builder().
                bankName(bankName).borrowerName(borrowerName).build();

        return LoanRequestDto.builder().bankInfoDto(bankInfoDto).
                principal(principal).
                noOfYears(noOfYears).
                rateOfInterest(rateOfInterest).
                build()
    }

    private PaymentRequestDto buildPaymentRequestDto(String bankName,
                                               String borrowerName,
                                               int lumpSumAmount,
                                               int emiNumber){
        BankInfoDto bankInfoDto = BankInfoDto.builder().
                bankName(bankName).borrowerName(borrowerName).build();

        return PaymentRequestDto.builder().bankInfoDto(bankInfoDto).
                lumpSumAmount(lumpSumAmount).
                emiNumber(emiNumber).
                build()
    }

    private BalanceRequestDto buildBalanceRequestDto(String bankName,
                                                     String borrowerName,
                                                     int emiNumber){
        BankInfoDto bankInfoDto = BankInfoDto.builder().
                bankName(bankName).borrowerName(borrowerName).build();

        return BalanceRequestDto.builder().bankInfoDto(bankInfoDto).
                emiNumber(emiNumber).
                build()
    }
}

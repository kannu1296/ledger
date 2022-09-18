package co.ledger.utility;

import co.ledger.dto.requestdto.BalanceRequestDto;
import co.ledger.dto.requestdto.BankInfoDto;
import co.ledger.dto.requestdto.LoanRequestDto;
import co.ledger.dto.requestdto.PaymentRequestDto;
import co.ledger.dto.responsedto.BalanceResponseDto;
import co.ledger.service.LedgerService;
import co.ledger.service.impl.LedgerServiceImpl;

/**
 * An invoker to invoke ledger commands
 */
class LedgerCommandInvoker {
    private static LedgerService ledger = new LedgerServiceImpl();

    public static void invokeLedgerMethods(String[] commandData){
        String command = commandData[0];
        String bankName = commandData[1];
        String borrowerName = commandData[2];
        BankInfoDto bankInfoDto = BankInfoDto.builder().
                bankName(bankName).borrowerName(borrowerName).build();

        switch (command){
            case "LOAN" :
                int principal = Integer.parseInt(commandData[3]);
                int noOfYears = Integer.parseInt(commandData[4]);
                int rateOfInterest = Integer.parseInt(commandData[5]);

                ledger.loan(LoanRequestDto.builder().bankInfoDto(bankInfoDto).
                        principal(principal).
                        noOfYears(noOfYears).
                        rateOfInterest(rateOfInterest).
                        build());
                break;

            case "PAYMENT" :
                int lumpSumAmount = Integer.parseInt(commandData[3]);
                int emiNoPayment = Integer.parseInt(commandData[4]);

                ledger.payment(PaymentRequestDto.builder().bankInfoDto(bankInfoDto).
                        lumpSumAmount(lumpSumAmount).
                        emiNumber(emiNoPayment).
                        build());
                break;

            case "BALANCE" :
                int emiNoBalance = Integer.parseInt(commandData[3]);

                printBalance(ledger.balance(BalanceRequestDto.builder().
                        bankInfoDto(bankInfoDto).
                        emiNumber(emiNoBalance).
                        build()));
                break;

            default :
                System.out.println("Invalid Command");

        }
    }

    private static void printBalance(BalanceResponseDto balanceResponseDto){
        System.out.println(balanceResponseDto);
    }
}

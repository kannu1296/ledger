package co.ledger.service

import co.ledger.service.impl.LedgerImpl
import spock.lang.Specification

class LedgerImplSpec extends Specification{
    def "test ledger impl loan mathod"(){

        given: "Bank name, Borrower name, Principal Amount, No of Years of Loan period and the Rate of Interest"
        Ledger ledger = new LedgerImpl()
        def bankName = "IDIDI"
        def borrowerName = "Dale"
        def principal = 10000
        def noOfYears = 5
        def rateOfInterest = 4

        when: "Loan Method is called"
        ledger.loan(bankName, borrowerName, principal, noOfYears, rateOfInterest)

        then: "Loan Info Should be created successfully"
        0*ledger.loan(_,_,_,_,_)
    }
}

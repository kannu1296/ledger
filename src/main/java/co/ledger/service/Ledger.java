package co.ledger.service;

/**
 * Interface to do Ledger activities
 */
public interface Ledger {
    void loan(String bankName, String borrowerName, int principal, int years, int ratOfInterest);
}

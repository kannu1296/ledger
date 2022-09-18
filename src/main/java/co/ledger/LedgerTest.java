package co.ledger;

import co.ledger.utility.ReadCommand;

public class LedgerTest {

    public static void main(String[] args) {

        String FILE_PATH = "src/main/resources/ledger_command.txt";

        ReadCommand.readFile(FILE_PATH);
    }
}

package co.ledger.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility to read input from file and process it according to
 * LOAN, PAYMENT, BALANCE Command
 */
public class ReadCommand {

    public static void readFile(String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while (line != null) {
                String[] commandData = line.split(" ");
                LedgerCommandInvoker.invokeLedgerMethods(commandData);
                line = br.readLine();
            }

        } catch (IOException excetion) {
            System.err.println("File Not Found, Please Enter Valid Path");
        }
    }

}

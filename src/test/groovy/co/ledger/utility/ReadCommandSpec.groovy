package co.ledger.utility

import spock.lang.Specification

class ReadCommandSpec extends  Specification{
    def "test read file"(){
        given: "file path"
        def filePath = "src/test/resources/ledger_command.txt"

        when: "read file method is called"
        ReadCommand.readFile(filePath)

        then: "should be able to read file successfully"
        1* ReadCommand.readFile(filePath)

    }
}

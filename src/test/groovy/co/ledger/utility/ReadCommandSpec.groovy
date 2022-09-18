package co.ledger.utility


import spock.lang.Specification

class ReadCommandSpec extends  Specification{
    def "test read file"(){
        given: "file path"
        def filePath = "src/test/resources/ledger_command.txt"

        when: "read file method is called"
        ReadCommand.readFile(filePath)

        then: "should be able to read file successfully"
        0*ReadCommand.readFile(_)
    }

    def "test read file with wrong file path"(){
        given: "file path"
        def filePath = "src/test/resources/ledger_comman.txt"

        when: "read file method is called"
        ReadCommand.readFile(filePath)

        then: "should through IOException"
        0*ReadCommand.readFile(_)
    }
}

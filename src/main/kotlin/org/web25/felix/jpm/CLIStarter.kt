package org.web25.felix.jpm

object CLIStarter {

    private val logger = Start.logger

    fun main(args: Array<String>) {
        logger.debug("Parsing command line options")
        val operationName = args[0]
        logger.debug("Starting operation $operationName")
        val operation = OperationFactory.build(operationName, args.copyOfRange(1, args.size))
        logger.debug("Operation of type ${operation.name} generated")
        operation.run()
    }

}


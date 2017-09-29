package org.web25.felix.jpm

object OperationFactory {

    fun build(name: String, args: Array<String>): Operation {
        return when(name) {
            "install" -> buildInstall(args[0], args[1])
            else -> throw JPMException("Invalid operation: $name")
        }
    }

    fun buildInstall(source: String, commandName: String): InstallOperation {
        return InstallOperation(source = source, commandName = commandName)
    }

}


package org.web25.felix.jpm.shell

interface ShellFile {

    fun setVar(name: String, value: String)

    fun runCommand(name: String, vararg args: String)

    fun printComment(comment: String)

    fun close()

}
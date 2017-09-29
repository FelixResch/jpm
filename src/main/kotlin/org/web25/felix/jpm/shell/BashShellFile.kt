package org.web25.felix.jpm.shell

import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter

class BashShellFile(outputStream: OutputStream) : ShellFile {

    val writer = PrintWriter(OutputStreamWriter(outputStream))

    init {
        writer.println("#!/usr/bin/env bash")
        writer.println()
    }

    override fun setVar(name: String, value: String) {
        writer.println("$name=$value")
        writer.println()
    }

    override fun printComment(comment: String) {
        writer.println("#$comment")
    }

    override fun runCommand(name: String, vararg args: String) {
        writer.print(name)
        args.forEach { arg ->
            writer.print(" \"$arg\"")
        }
        writer.println()
    }

    override fun close() {
        writer.close()
    }
}
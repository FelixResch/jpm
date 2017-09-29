package org.web25.felix.jpm.path

import org.web25.felix.jpm.os.os
import java.io.File

object PathContext {

    val path = System.getenv("PATH")

    fun checkForExecutable(name: String): Boolean {
        val pathDirs = pathComponents().map { File(it) }
        return pathDirs.any { path ->
            val execPath = File(path, name)
            execPath.isFile && execPath.canExecute()
        }
    }

    fun pathComponents(): List<String> {
        return os<List<String>> {
            linux {
                path.split(":")
            }
            windows {
                path.split(";")
            }
        }.result?: listOf()
    }
}
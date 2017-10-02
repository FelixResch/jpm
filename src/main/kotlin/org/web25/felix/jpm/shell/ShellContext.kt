package org.web25.felix.jpm.shell

import org.web25.felix.jpm.os.os
import org.web25.felix.jpm.path.PathContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

object ShellContext {
    fun createWriteableShellFile(file: File): ShellFile {
        file.parentFile.mkdirs()
        file.setExecutable(true)
        return createWriteableShellFile(FileOutputStream(file))
    }

    fun createWriteableShellFile(outputStream: OutputStream): ShellFile {
        return os<ShellFile> {
            windows {
                TODO("Add powershell support")
            }
            other {
                if (PathContext.checkForExecutable("bash")) {
                    BashShellFile(outputStream)
                } else {
                    TODO("Write fallback solution")
                }
            }
        }.result?: TODO("Add support for more shells")
    }


}


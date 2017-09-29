package org.web25.felix.jpm

import org.web25.felix.jpm.shell.ShellFile

interface PacketInstaller {
    fun install(installConfig: InstallConfig)
    fun createRunnable(shellFile: ShellFile)

}
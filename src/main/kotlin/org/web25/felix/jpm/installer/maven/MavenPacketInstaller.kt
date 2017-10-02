package org.web25.felix.jpm.installer.maven

import org.apache.maven.shared.invoker.DefaultInvocationRequest
import org.apache.maven.shared.invoker.DefaultInvoker
import org.web25.felix.jpm.InstallConfig
import org.web25.felix.jpm.Packet
import org.web25.felix.jpm.PacketInstaller
import org.web25.felix.jpm.shell.ShellFile
import java.io.File

class MavenPacketInstaller(val packet: Packet) : PacketInstaller {

    override fun install(installConfig: InstallConfig) {
        val invocationRequest = DefaultInvocationRequest()
        invocationRequest.baseDirectory = installConfig.installPath
        invocationRequest.goals = listOf("clean", "package")

        val invoker = DefaultInvoker()
        invoker.workingDirectory = installConfig.installPath
        invoker.mavenHome = File("/usr/share/java/maven/")
        invoker.execute(invocationRequest)
    }

    override fun createRunnable(shellFile: ShellFile) {
        val installConfig = packet.jobContext[InstallConfig::class]
        shellFile.runCommand("java", "-jar", File(installConfig.installPath, "target").absolutePath + "/hello-0.1.0.jar")
    }
}
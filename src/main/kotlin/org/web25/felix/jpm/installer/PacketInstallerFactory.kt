package org.web25.felix.jpm.installer

import org.web25.felix.jpm.InstallConfig
import org.web25.felix.jpm.Packet
import org.web25.felix.jpm.PacketInstaller
import org.web25.felix.jpm.installer.gradle.GradlePacketInstaller
import org.web25.felix.jpm.installer.maven.MavenPacketInstaller
import java.io.File

object PacketInstallerFactory {

    fun create(packet: Packet): PacketInstaller {
        val installConfig = packet.jobContext[InstallConfig::class]
        if (File(installConfig.installPath, "pom.xml").exists()) {
            return MavenPacketInstaller(packet)
        } else if (File(installConfig.installPath, "build.gradle").exists()) {
            return GradlePacketInstaller()
        } else {
            TODO("Implement other build systems")
        }
    }
}


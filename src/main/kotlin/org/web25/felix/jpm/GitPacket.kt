package org.web25.felix.jpm

import org.web25.felix.jpm.installer.PacketInstallerFactory
import org.web25.felix.jpm.job.JobContext
import java.net.URI

class GitPacket(val source: String, jobContext: JobContext) : Packet(jobContext) {

    override val packetDownloader: PacketDownloader = GitPacketDownloader(source)

    override val dependencyIterator: DependencyIterator = object : DependencyIterator {
        override val packets: MutableList<Packet>
            get() = mutableListOf()

    }

    override val binaryPath: String
        get() {
            val uri = URI.create(source)
            var path = uri.path
            if (path.endsWith(".git")) {
               path = path.substring(0, path.length - 4)
            }
            return "${uri.host}$path"
        }

    override val packetInstaller: PacketInstaller by lazy {
        PacketInstallerFactory.create(this)
    }

    override fun fetchDependencies(): List<Packet> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


package org.web25.felix.jpm

import java.net.URI

class GitPacket(val source: String) : Packet() {

    override val packetDownloader: PacketDownloader = GitPacketDownloader(source)

    override val dependencyIterator: DependencyIterator
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val binaryPath: String
        get() {
            val uri = URI.create(source)
            var path = uri.path
            if (path.endsWith(".git")) {
               path = path.substring(0, path.length - 4)
            }
            return "${uri.host}$path"
        }

    override val packetInstaller: PacketInstaller
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun fetchDependencies(): List<Packet> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


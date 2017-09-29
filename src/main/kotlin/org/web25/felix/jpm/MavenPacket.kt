package org.web25.felix.jpm

class MavenPacket(source: String) : Packet() {

    val groupId: String

    val artifactId: String

    val version: String?

    init {
        val parts = source.split(":")
        groupId = parts[0]
        artifactId = parts[1]
        if(parts.size == 2) {
            version = null
        } else {
            version = parts[2]
        }
    }

    override val packetDownloader: PacketDownloader
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val dependencyIterator: DependencyIterator
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun fetchDependencies(): List<Packet> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val binaryPath: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val packetInstaller: PacketInstaller
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toString(): String {
        return "MavenPacket(groupId='$groupId', artifactId='$artifactId', version=$version)"
    }


}
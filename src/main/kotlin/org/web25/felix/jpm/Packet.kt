package org.web25.felix.jpm

abstract class Packet {

    abstract val packetDownloader : PacketDownloader
    abstract val dependencyIterator: DependencyIterator
    abstract fun fetchDependencies(): List<Packet>
    abstract val binaryPath: String
    abstract val packetInstaller: PacketInstaller

}


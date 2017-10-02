package org.web25.felix.jpm

import org.web25.felix.jpm.job.JobContext

abstract class Packet(val jobContext: JobContext) {

    abstract val packetDownloader : PacketDownloader
    abstract val dependencyIterator: DependencyIterator
    abstract fun fetchDependencies(): List<Packet>
    abstract val binaryPath: String
    abstract val packetInstaller: PacketInstaller

}


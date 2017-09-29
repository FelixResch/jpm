package org.web25.felix.jpm

interface DependencyIterator {

    val packets: MutableList<Packet>

    fun forEach (block: (packet: Packet) -> List<Packet>) {
        packets.forEach { packet ->
            val packets = block(packet)
            this.packets.addAll(packets)
        }
    }
}
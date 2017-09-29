package org.web25.felix.jpm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GitPacketTest {

    @Test
    internal fun testBinaryPath() {
        val gitPacket = GitPacket("https://github.com/FelixResch/jpm-hello.git")
        assertEquals("github.com/FelixResch/jpm-hello", gitPacket.binaryPath)
    }
}
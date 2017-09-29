package org.web25.felix.jpm.os

class OsTools<T>(block: OsTools<T>.() -> Unit) {

    private var windowsEx: (() -> T)? = null
    private var linuxEx: (() -> T)? = null
    private var otherEx: (() -> T)? = null

    val result : T?

    init {
        this.block()
        val os = System.getProperty("os.name").toLowerCase()
        result = when(os) {
            "windows" -> {
                val ex = windowsEx
                if(ex != null) {
                    ex()
                } else {
                    otherEx?.let { it() }
                }
            }
            "linux" -> {
                val ex = linuxEx
                if(ex != null) {
                    ex()
                } else {
                    otherEx?.let { it() }
                }
            }
            else -> otherEx?.let { it() }
        }
    }

    fun linux(block: () -> T) {
        linuxEx = block
    }

    fun windows(block: () -> T) {
        windowsEx = block
    }

    fun other(block: () -> T) {
        otherEx = block
    }
}

fun <T> os(block: OsTools<T>.() -> Unit) =  OsTools(block)
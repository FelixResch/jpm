package org.web25.felix.jpm

import java.io.File

class GlobalConfig {

    var userDir: File
    get() = File(System.getProperty("user.home"))
    set(_) {}

    var jpmDir: File
    get() = File(userDir, ".jpm")
    set(_) {}

    var binDir: File
    get() = File(jpmDir, "bin")
    set(_) {}

    var resDir: File
    get() = File(jpmDir, "res")
    set(_) {}
}
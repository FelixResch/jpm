package org.web25.felix.jpm.shell

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream

internal class BashShellFileTest {

    @Test
    internal fun testShebang() {
        val buffer = ByteArrayOutputStream()
        val shellFile = ShellContext.createWriteableShellFile(buffer)
        shellFile.close()
        val script = buffer.toString()
        assertEquals("#!/usr/bin/env bash\n\n", script)
    }

    @Test
    internal fun testComment() {
        val buffer = ByteArrayOutputStream()
        val shellFile = ShellContext.createWriteableShellFile(buffer)
        shellFile.printComment("This is a comment")
        shellFile.close()
        val script = buffer.toString()
        assertEquals("#!/usr/bin/env bash\n\n#This is a comment\n", script)
    }

    @Test
    internal fun testVariable() {
        val buffer = ByteArrayOutputStream()
        val shellFile = ShellContext.createWriteableShellFile(buffer)
        shellFile.setVar("name", "\"Test\"")
        shellFile.close()
        val script = buffer.toString()
        assertEquals("#!/usr/bin/env bash\n\nname=\"Test\"\n\n", script)
    }

    @Test
    internal fun testRunCommand() {
        val buffer = ByteArrayOutputStream()
        val shellFile = ShellContext.createWriteableShellFile(buffer)
        shellFile.runCommand("echo", "Hello World")
        shellFile.runCommand("ls", "-al", "/usr/bin")
        shellFile.close()
        val script = buffer.toString()
        assertEquals("#!/usr/bin/env bash\n\necho \"Hello World\"\nls \"-al\" \"/usr/bin\"\n", script)
    }

    @Test
    internal fun testFullScript() {
        val buffer = ByteArrayOutputStream()
        val shellFile = ShellContext.createWriteableShellFile(buffer)
        shellFile.printComment("Simple start script for jpm command hello-world (jpm.hello-world)")
        shellFile.setVar("pathToJar", "~/jpm/res/org/web25/jpm/hello-world/1.0.0/hello-world-1.0.0.jar")
        shellFile.runCommand("java", "-jar", "\$pathToJar")
        shellFile.close()
        val script = buffer.toString()
        assertEquals("#!/usr/bin/env bash\n\n#Simple start script for jpm command hello-world (jpm.hello-world)\npathToJar=~/jpm/res/org/web25/jpm/hello-world/1.0.0/hello-world-1.0.0.jar\n\njava \"-jar\" \"\$pathToJar\"\n", script)
    }
}
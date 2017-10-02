package org.web25.felix.jpm

import org.web25.felix.jpm.job.Job
import org.web25.felix.jpm.job.JobContext
import org.web25.felix.jpm.shell.ShellContext
import java.io.File
import java.nio.file.Files

class InstallOperation(val source: String, val commandName: String): Operation {

    override val name: String = "install"

    override fun buildJobList(jobContext: JobContext) {
        jobContext.build {
            run {
                if (source.startsWith("https://github.com/") || source.startsWith("git@github.com")) {
                    addGitPacket()
                } else {
                    lookupPacket()
                }
            }
            run {
                createConfig()
            }
            all {
                run { downloadPacket() }
                run { downloadDependencies() }
            }
            run { buildPacket() }
            run {
                createRunnable()
            }
        }
    }

    fun createRunnable(): Job {
        return object : Job {
            override fun execute(jobContext: JobContext) {
                val packet = jobContext[Packet::class]
                val globalConfig = jobContext[GlobalConfig::class]
                val installConfig = jobContext[InstallConfig::class]
                val jpmExecutable = File(globalConfig.binDir, "jpm." + installConfig.commandName)
                val shellFile = ShellContext.createWriteableShellFile(jpmExecutable)
                packet.packetInstaller.createRunnable(shellFile)
                shellFile.close()
                val link = Files.createSymbolicLink(File(globalConfig.binDir, installConfig.commandName).toPath(), jpmExecutable.toPath())
                link.toFile().setExecutable(true)
                Start.logger.info("Installation complete!")
            }
        }
    }

    fun createConfig(): Job {
        return object : Job {
            override fun execute(jobContext: JobContext) {
                val packet = jobContext[Packet::class]
                val globalConfig = jobContext[GlobalConfig::class]
                val installConfig = InstallConfig(
                        commandName = commandName,
                        installPath = File(globalConfig.resDir, packet.binaryPath)
                )
                jobContext[InstallConfig::class] = installConfig
            }
        }
    }

    private fun downloadDependencies(): Job {
        return object : Job {
            override fun execute(jobContext: JobContext) {
                val packet = jobContext[Packet::class]
                val installConfig = jobContext[InstallConfig::class]
                val dependencyIterator = packet.dependencyIterator
                dependencyIterator.forEach { p ->
                    p.packetDownloader.download(jobContext, installConfig)
                    p.fetchDependencies()

                }
            }
        }
    }

    private fun downloadPacket(): Job {
        return object : Job {
            override fun execute(jobContext: JobContext) {
                val packet = jobContext[Packet::class]
                val installConfig = jobContext[InstallConfig::class]
                val downloader = packet.packetDownloader
                downloader.download(jobContext, installConfig)
            }
        }
    }

    private fun addGitPacket(): Job {
        return object : Job {

            override fun execute(jobContext: JobContext) {
                jobContext[Packet::class] = GitPacket(source, jobContext)
            }

        }
    }

    private fun lookupPacket(): Job {
        return object : Job {

            override fun execute(jobContext: JobContext) {
                jobContext[Packet::class] = MavenPacket(source, jobContext)
            }
        }
    }

    private fun buildPacket(): Job {
        return object : Job {
            override fun execute(jobContext: JobContext) {
                val packet = jobContext[Packet::class]
                val installConfig = jobContext[InstallConfig::class]
                val installer = packet.packetInstaller
                installer.install(installConfig)
            }

        }
    }
}


package org.web25.felix.jpm

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.ProgressMonitor
import org.web25.felix.jpm.job.JobContext
import org.web25.felix.jpm.progress.ProgressPrinter
import java.io.File

class GitPacketDownloader(val source: String) : PacketDownloader {

    override fun download(jobContext: JobContext, installConfig: InstallConfig) {
        val progressPrinter = jobContext[ProgressPrinter::class]
        val packet = jobContext[Packet::class]
        val globalConfig = jobContext[GlobalConfig::class]
        val task = progressPrinter.createTask("Cloning $source")
        val dest = File(globalConfig.resDir, packet.binaryPath)
        Start.logger.debug(dest.toString())
        Git.cloneRepository()
                .setURI(source)
                .setDirectory(dest)
                .setProgressMonitor(object : ProgressMonitor {

                    override fun update(completed: Int) {
                        task.subtaskProgress = completed
                    }

                    override fun start(totalTasks: Int) {
                        task.progressTotal = totalTasks
                    }

                    override fun beginTask(title: String, totalWork: Int) {
                        task.subtask = title
                        task.subtaskTotal = totalWork
                    }

                    override fun endTask() {
                        task.subtaskTotal = task.subtaskTotal
                        task.progress++
                    }

                    override fun isCancelled(): Boolean {
                        return false
                    }
                }).call()
    }

}
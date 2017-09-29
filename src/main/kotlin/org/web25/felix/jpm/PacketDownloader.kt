package org.web25.felix.jpm

import org.web25.felix.jpm.job.JobContext

interface PacketDownloader {

    fun download(jobContext: JobContext, installConfig: InstallConfig)
}
package org.web25.felix.jpm

import org.web25.felix.jpm.job.JobContext
import org.web25.felix.jpm.job.JobContextFactory

interface Operation {

    val name: String

    fun buildJobList(jobContext: JobContext)

    fun run() {
        val jobContext = JobContextFactory.buildJobContext()
        this.buildJobList(jobContext)
        jobContext.execute()
    }
}


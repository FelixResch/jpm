package org.web25.felix.jpm.job

import org.web25.felix.jpm.job.DefaultJobContext
import org.web25.felix.jpm.job.JobContext

object JobContextFactory {
    fun buildJobContext(): JobContext = DefaultJobContext()

}
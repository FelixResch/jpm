package org.web25.felix.jpm.job

interface Job {

    fun execute(jobContext: JobContext)

}
package org.web25.felix.jpm.job

import java.util.concurrent.atomic.AtomicBoolean

class DefaultJobRunner(val jobContext: JobContext = DefaultJobContext()) : JobRunner {

    private val jobs = mutableListOf<Job>()

    private val atFinished = AtomicBoolean(false)

    override fun run(block: JobRunner.() -> Job) {
        synchronized(jobs) {
            jobs.add(this.block())
        }
    }

    override fun buildNewParallelRunner(): JobRunner {
        //TODO add parallel runner
        return DefaultJobRunner(jobContext)
    }

    override fun awaitAll() {
        while (!atFinished.get());
    }

    override fun execute() {
        jobs.forEach { job ->
            job.execute(jobContext)
        }
        atFinished.set(true)
    }
}
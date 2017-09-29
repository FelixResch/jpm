package org.web25.felix.jpm.job

interface JobRunner {

    fun run(block : JobRunner.() -> Job)

    fun all(block: JobRunner.() -> Unit) {
        val runner = buildNewParallelRunner()
        runner.block()
        this.run {
            object: Job {
                override fun execute(jobContext: JobContext) {
                    runner.execute()
                    runner.awaitAll()
                }

            }
        }
    }

    fun buildNewParallelRunner(): JobRunner

    fun awaitAll()

    fun execute()

}
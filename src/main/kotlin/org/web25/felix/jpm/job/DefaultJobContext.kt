package org.web25.felix.jpm.job

import org.web25.felix.jpm.GlobalConfig
import org.web25.felix.jpm.progress.PlainTextProgressPrinter
import org.web25.felix.jpm.progress.ProgressPrinter
import kotlin.reflect.KClass

class DefaultJobContext : JobContext {

    private val map = mutableMapOf<KClass<*>, Any>()

    private val runners = mutableListOf<JobRunner>()

    init {
        this[ProgressPrinter::class] = PlainTextProgressPrinter()
        this[GlobalConfig::class] = GlobalConfig()
    }

    override fun buildJobRunner(add : Boolean): JobRunner {
        val jobRunner = DefaultJobRunner(this)
        if(runners.isEmpty()) {
            synchronized(runners) {
                runners.add(jobRunner)
            }
        }
        return jobRunner
    }

    override fun <T : Any> set(clazz: KClass<T>, value: T) {
        map[clazz] = value
    }

    override fun <T : Any> get(clazz: KClass<T>): T {
        val value = map[clazz]
        if(value != null) {
            return value as T
        } else {
            throw JobContextException("No Element for type $clazz found")
        }
    }

    override fun execute() {
        synchronized(runners) {
            runners.first().let { runner ->
                runner.execute()
                runner.awaitAll()
            }
        }
    }
}


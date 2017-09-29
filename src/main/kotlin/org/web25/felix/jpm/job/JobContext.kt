package org.web25.felix.jpm.job

import kotlin.reflect.KClass

interface JobContext {

    fun buildJobRunner(add: Boolean = false): JobRunner

    fun build(block : JobRunner.() -> Unit) {
        val runner = buildJobRunner()
        runner.block()
    }

    operator fun <T: Any> set(clazz: KClass<T>, value: T)

    operator fun <T: Any> get(clazz: KClass<T>): T

    fun execute()

}


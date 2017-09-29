package org.web25.felix.jpm.progress

import kotlin.concurrent.thread

class PlainTextProgressPrinter : ProgressPrinter {

    private val tasks = mutableListOf<Task>()

    init {
        thread(start = true) {
            Thread.sleep(500)
            tasks.filter(Task::finished).forEach {
                println(it)
            }
        }
    }

    override fun createTask(name: String): Task {
        return synchronized(tasks) {
            val task = BasicTask(name)
            tasks.add(task)
            task
        }
    }
}
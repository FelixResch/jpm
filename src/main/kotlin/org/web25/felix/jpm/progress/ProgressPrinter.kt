package org.web25.felix.jpm.progress

interface ProgressPrinter {

    fun createTask(name: String): Task

}
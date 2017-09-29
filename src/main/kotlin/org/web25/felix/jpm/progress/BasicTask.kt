package org.web25.felix.jpm.progress

import org.web25.felix.jpm.Start

class BasicTask(val name: String) : Task {

    private val logger = Start.logger

    override var subtask: String = ""
    set(value) {
        logger.debug("Subtask: $subtask")
        field = value
    }

    override var subtaskProgress: Int = 0
    set(value) {
        logger.debug("Subtask Progress: $subtaskProgress")
        field = value
    }

    override var progress: Int = 0
    set(value) {
        logger.debug("Progress: $progress")
        field = value
    }

    override var progressTotal: Int = 0
    set(value) {
        logger.debug("Progress Total: $progressTotal")
        field = value
    }

    override var subtaskTotal: Int = 0
    set(value) {
        logger.debug("Subtask Total: $subtaskTotal")
        field = value
    }

    override fun toString(): String {
        return "BasicTask(name='$name', subtask='$subtask', subtaskProgress=$subtaskProgress, progress=$progress, progressTotal=$progressTotal, subtaskTotal=$subtaskTotal)"
    }


}
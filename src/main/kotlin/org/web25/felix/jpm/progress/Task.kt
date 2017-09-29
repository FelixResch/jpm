package org.web25.felix.jpm.progress

interface Task {

    var subtask: String

    var subtaskProgress: Int

    var progress: Int

    var progressTotal: Int

    var subtaskTotal: Int

    var finished: Boolean
    get() = progress == progressTotal
    set(value) {}

}
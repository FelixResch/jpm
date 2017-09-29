package org.web25.felix.jpm

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.GraphicsEnvironment

object Start {

    val logger: Logger = LoggerFactory.getLogger("JPM")

    @JvmStatic
    fun main(args: Array<String>) {
        logger.info("Starting JPM version ${JPM.VERSION}")
        if (args.isNotEmpty()) {
            //TODO start cli mode
            CLIStarter.main(args)
        } else if (args.size == 0) {
            if (GraphicsEnvironment.isHeadless()) {
                //TODO start cli prompt
            } else {
                //TODO start gui
            }
        }
    }
}


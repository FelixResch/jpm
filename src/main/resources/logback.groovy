
appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy-MM-dd hh:mm:ss:sss} %5level [%logger{0}@%thread] %msg%n"
    }
}

root(DEBUG, ["STDOUT"])

logger("JPM", DEBUG, ["STDOUT"], false)

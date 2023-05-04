package dev.mcd.logtask.feature.log.ui.formatter

import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.Locale

val logTimeUIFormatter: DateTimeFormatter = DateTimeFormatterBuilder()
    .appendValue(ChronoField.HOUR_OF_DAY, 2)
    .appendLiteral(":")
    .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
    .toFormatter(Locale.UK)

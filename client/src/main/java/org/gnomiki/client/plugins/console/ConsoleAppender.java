package org.gnomiki.client.plugins.console;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

public class ConsoleAppender extends AppenderSkeleton {

	static Logger L = Logger.getLogger(ConsoleAppender.class);

	static ConsoleAppender me;

	ConsoleTable table;

	public ConsoleAppender() {
		me = this;
	}

	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void append(LoggingEvent le) {
		if (table == null) {
			return;
		}
		String severity = Priority.toPriority(le.getLevel().toInt()).toString();
		Object message = le.getMessage();
		Throwable t = le.getThrowableInformation() != null ? le
				.getThrowableInformation().getThrowable() : null;
		table.addEntry(le.getLoggerName(), severity, message, t);

	}

	public void close() {
		// TODO Auto-generated method stub

	}
}

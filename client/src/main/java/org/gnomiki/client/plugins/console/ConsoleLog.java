package org.gnomiki.client.plugins.console;

import javax.swing.JTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConsoleLog implements Log {

	Log delegate;
	private final ConsoleTable table;
	private final Class clazz;

	@SuppressWarnings("unchecked")
	public ConsoleLog(Class clazz, ConsoleTable table) {
		this.clazz = clazz;
		this.table = table;
		delegate = LogFactory.getLog(clazz);
	}

	public void debug(Object message, Throwable t) {
		delegate.debug(message, t);
		table.addDebug(clazz, message, t);

	}

	public void debug(Object message) {
		delegate.debug(message);
		table.addDebug(clazz, message, null);

	}

	public void error(Object message, Throwable t) {
		delegate.error(message, t);
		table.addError(clazz, message, t);

	}

	public void error(Object message) {
		delegate.error(message);
		table.addError(clazz, message, null);
	}

	public void fatal(Object message, Throwable t) {
		delegate.fatal(message, t);
		table.addFatal(clazz, message, t);

	}

	public void fatal(Object message) {
		delegate.fatal(message);
		table.addFatal(clazz, message, null);

	}

	public void info(Object message, Throwable t) {
		delegate.info(message, t);
		table.addInfo(clazz, message, t);

	}

	public void info(Object message) {
		delegate.info(message);
		table.addInfo(clazz, message, null);

	}

	public boolean isDebugEnabled() {
		return delegate.isDebugEnabled();

	}

	public boolean isErrorEnabled() {
		return delegate.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return delegate.isFatalEnabled();
	}

	public boolean isInfoEnabled() {
		return delegate.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return delegate.isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		return delegate.isWarnEnabled();
	}

	public void trace(Object message, Throwable t) {
		delegate.trace(message, t);
	}

	public void trace(Object message) {
		delegate.trace(message);
	}

	public void warn(Object message, Throwable t) {
		delegate.warn(message, t);
		table.addWarn(clazz, message, t);
	}

	public void warn(Object message) {
		delegate.warn(message);
		table.addWarn(clazz, message, null);
	}

}

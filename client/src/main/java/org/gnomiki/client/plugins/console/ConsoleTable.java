package org.gnomiki.client.plugins.console;

import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

;

public class ConsoleTable extends JTable {

	DefaultTableModel model;

	public ConsoleTable() {
		model = new DefaultTableModel(new Object[] { "Time", "SEV", "SOURCE",
				"MSG", "Exc" }, 3);
		setModel(model);

	}

	void addTrace(Class clazz, String msg, Throwable t) {
		addEntry(clazz, "TRACE", msg, t);
	}

	void addDebug(Class clazz, Object message, Throwable t) {
		addEntry(clazz, "DEBUG", message, t);
	}

	private void addEntry(Class clazz, String severity, Object message,
			Throwable t) {

		Object[] rowData = new Object[] { new Date(), severity,
				clazz.getSimpleName(), message, t != null ? t.toString() : "" };
		model.insertRow(0, rowData);

	}

	void addInfo(Class clazz, Object msg, Throwable t) {
		addEntry(clazz, "INFO", msg, t);
	}

	void addError(Class clazz, Object msg, Throwable t) {
		addEntry(clazz, "ERROR", msg, t);
	}

	void addWarn(Class clazz, Object msg, Throwable t) {
		addEntry(clazz, "WARN", msg, t);
	}

	void addFatal(Class clazz, Object msg, Throwable t) {
		addEntry(clazz, "FATAL", msg, t);
	}

}

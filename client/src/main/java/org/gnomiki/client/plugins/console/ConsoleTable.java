package org.gnomiki.client.plugins.console;

import java.text.DateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

;

public class ConsoleTable extends JTable {

	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,
			DateFormat.SHORT);

	DefaultTableModel model;

	public ConsoleTable() {
		model = new DefaultTableModel(new Object[] { "Time", "SEV", "SOURCE",
				"MSG", "Exc" }, 1);
		setModel(model);

	}

	void addTrace(String name, String msg, Throwable t) {
		addEntry(name, "TRACE", msg, t);
	}

	void addDebug(String name, Object message, Throwable t) {
		addEntry(name, "DEBUG", message, t);
	}

	void addEntry(String name, String severity, Object message, Throwable t) {

		Object[] rowData = new Object[] { dateFormat.format(new Date()),
				severity, name, message, t != null ? t.toString() : "" };
		model.insertRow(0, rowData);

	}

	void addInfo(String name, Object msg, Throwable t) {
		addEntry(name, "INFO", msg, t);
	}

	void addError(String name, Object msg, Throwable t) {
		addEntry(name, "ERROR", msg, t);
	}

	void addWarn(String name, Object msg, Throwable t) {
		addEntry(name, "WARN", msg, t);
	}

	void addFatal(String name, Object msg, Throwable t) {
		addEntry(name, "FATAL", msg, t);
	}

}

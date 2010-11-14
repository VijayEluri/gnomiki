package org.gnomiki.client.plugins.console;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.gnomiki.client.plugins.IPluginView;

@SuppressWarnings("serial")
public class ConsolePanel extends JPanel implements IPluginView {

	private ConsoleTable table;

	public ConsolePanel() {
		table = new ConsoleTable();
		table.setPreferredSize(new Dimension(600, 100));
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane);
	}

	public JComponent getComponent() {
		return this;
	}

	public String getPreferredRegion() {
		return BorderLayout.SOUTH;
	}

	public ConsoleTable getTable() {
		return table;
	}

	public String getTitle() {
		return "Console";
	}

}

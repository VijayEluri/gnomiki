package org.gnomiki.client.plugins.console;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.client.plugins.ISwingPlugin;
import org.gnomiki.core.config.Configuration;
import org.gnomiki.plugins.IPluginManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Plugin that puts out log output done via commons logging to a view.
 * 
 * @author MicWin
 * 
 */
public class ConsolePlugin implements ISwingPlugin {

	public static final String PLUGIN_ID = "guiLog";

	private final Log L = LogFactory.getLog(ConsolePlugin.class);

	@Autowired
	private Configuration config;

	private JPanel panel;

	private IPluginManager pluginManager;

	private ConsoleTable table;

	public String getPluginId() {
		return PLUGIN_ID;
	}

	public void init(IPluginManager pm) throws Exception {
		this.pluginManager = pm;
		L.info("initialized");
	}

	public void shutDown() {
		L.info("shutdown");
	}

	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel(new BorderLayout());
			JScrollPane scrollPane = new JScrollPane(getTable(),
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panel.add(scrollPane, BorderLayout.CENTER);
		}
		return panel;
	}

	public ConsoleTable getTable() {
		if (table == null) {
			table = new ConsoleTable();

		}
		return table;
	}

	public void info(Class clazz, String msg) {
		LogFactory.getLog(clazz).info(msg);
	}

	public Log getLog(Class clazz) {
		return new ConsoleLog(clazz, getTable());
	}

	public JMenu getMenu() {
		JMenu menu = new JMenu("Console");
		JMenuItem clearItem = new JMenuItem(new Action() {

			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) getTable()
						.getModel();
				model.setRowCount(0);
			}

			public void setEnabled(boolean b) {
			}

			public void removePropertyChangeListener(
					PropertyChangeListener listener) {
			}

			public void putValue(String key, Object value) {
			}

			public boolean isEnabled() {

				return true;
			}

			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}

			public void addPropertyChangeListener(
					PropertyChangeListener listener) {
				// TODO Auto-generated method stub

			}
		});

		clearItem.setText("Clear");

		menu.add(clearItem);
		menu.setText(getTitle());
		return menu;
	}

	public JDialog getDialog(JFrame parent) {
		return null;
	}

	public String getTitle() {
		return "Console";
	}
}

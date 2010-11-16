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
import org.gnomiki.client.plugins.IPlugin;
import org.gnomiki.client.plugins.IPluginManager;
import org.gnomiki.client.plugins.IPluginView;
import org.gnomiki.core.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Plugin that puts out log output done via commons logging to a view.
 * 
 * @author MicWin
 * 
 */
public class ConsolePlugin implements IPlugin, IPluginView {

	public static final String PLUGIN_IN = "guiLog";

	private final Log L = LogFactory.getLog(ConsolePlugin.class);

	@Autowired
	private Configuration config;

	private JPanel panel;

	private IPluginManager pluginManager;

	private ConsoleTable table;

	public String getPluginId() {
		return PLUGIN_IN;
	}

	public void init(IPluginManager pm) throws Exception {
		this.pluginManager = pm;
		L.info("initialized");
	}

	public void shutDown() {
		L.info("shutdown");
	}

	public IPluginView getView() {
		return this;
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

	@Override
	public JMenu getMenu() {
		JMenu menu = new JMenu("Console");
		JMenuItem clearItem = new JMenuItem(new Action() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) getTable()
						.getModel();
				model.setRowCount(0);
			}

			@Override
			public void setEnabled(boolean b) {
			}

			@Override
			public void removePropertyChangeListener(
					PropertyChangeListener listener) {
			}

			@Override
			public void putValue(String key, Object value) {
			}

			@Override
			public boolean isEnabled() {

				return true;
			}

			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
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

	@Override
	public JDialog getDialog(JFrame parent) {
		return null;
	}

	@Override
	public String getTitle() {
		return "Console";
	}
}

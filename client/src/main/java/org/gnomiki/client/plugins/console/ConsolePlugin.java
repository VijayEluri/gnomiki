package org.gnomiki.client.plugins.console;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.client.plugins.swing.SwingLayouter;
import org.gnomiki.config.Configuration;
import org.gnomiki.plugins.IPlugin;
import org.gnomiki.plugins.IPluginManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Plugin that puts out log output done via log4j to a view. You implcitely
 * use this if you use commons logging and have log4j in the path.
 * 
 * @author MicWin
 * 
 */
public class ConsolePlugin implements IPlugin {

	public static final String PLUGIN_ID = "guiLog";

	private final Log L = LogFactory.getLog(ConsolePlugin.class);

	@Autowired
	private Configuration config;

	private JDialog dialog;

	private IPluginManager pluginManager;

	private ConsoleTable table;

	public String getPluginId() {
		return PLUGIN_ID;
	}

	public void init(IPluginManager pm) throws Exception {
		this.pluginManager = pm;

		dialog = new JDialog();
		dialog.setContentPane(composePanel());
		dialog.setJMenuBar(getMenuBar());

		SwingLayouter slp = (SwingLayouter) pluginManager
				.getPlugin(SwingLayouter.PLUGIN_ID);

		slp.layout(dialog, SwingLayouter.DialogType.CONSOLE);
		dialog.setVisible(true);
		ConsoleAppender.me.table = getTable();
		L.info("initialized");
	}

	private JPanel composePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(getTable(),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane, BorderLayout.CENTER);
		return panel;
	}

	public void shutDown() {
		L.info("shutdown");
		ConsoleAppender.me.table = null;
		dialog.setVisible(false);
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

	public JMenuBar getMenuBar() {
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

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		return menuBar;
	}

	public JDialog getDialog(JFrame parent) {
		return null;
	}

	public String getTitle() {
		return "Console";
	}
}

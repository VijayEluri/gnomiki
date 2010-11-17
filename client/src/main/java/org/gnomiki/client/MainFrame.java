package org.gnomiki.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.client.plugins.ISwingPlugin;
import org.gnomiki.client.plugins.console.ConsolePlugin;
import org.gnomiki.core.config.Configuration;
import org.gnomiki.plugins.IPlugin;
import org.gnomiki.plugins.IPluginManager;
import org.springframework.beans.factory.annotation.Autowired;

public class MainFrame extends JFrame implements WindowListener {

	private static final String[] REGION_ORDER = { BorderLayout.CENTER,
			BorderLayout.NORTH, BorderLayout.SOUTH, BorderLayout.WEST,
			BorderLayout.EAST };

	@Autowired
	ResourceBundle bundle;
	private static final Log L = LogFactory.getLog(MainFrame.class);

	@Autowired
	private Configuration config;

	@Autowired
	private IPluginManager pluginManager;
	private HashMap<String, JTabbedPane> tabsMap;

	private JMenu widgetMenu;

	public MainFrame() {
		super("Gnomiki Client");
	}

	public void init() {
		setJMenuBar(composeMenuBar());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Dimension screenSize = getToolkit().getScreenSize();
		setSize(new Dimension((int) screenSize.getWidth(),
				(int) (screenSize.getHeight() / 4)));

		setLocation(0, (int) (screenSize.getHeight() - getHeight()));

		IPlugin[] plugins = pluginManager.getPlugins();
		for (IPlugin iPlugin : plugins) {
			try {
				registerPlugin((ISwingPlugin) iPlugin);
			} catch (ClassCastException cce) {
				if (L.isDebugEnabled())
					L.debug("plugin '" + iPlugin.getPluginId()
							+ "' not a swing plugin");
			}
		}
		configureContentPane();
		addWindowListener(this);
	}

	private void registerPlugin(ISwingPlugin iPlugin) {

		JMenu menu = iPlugin.getMenu();
		if (menu != null) {
			widgetMenu.add(menu);
		}

		JDialog dialog = iPlugin.getDialog(this);
		if (dialog == null)
			return;
		dialog.setVisible(true);
	}

	private void configureContentPane() {
		ConsolePlugin console = (ConsolePlugin) pluginManager
				.getPlugin(ConsolePlugin.PLUGIN_ID);
		setContentPane(console.getPanel());

	}

	private JMenuBar composeMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		widgetMenu = new JMenu(bundle.getString("menu.label.widgets"));
		menuBar.add(widgetMenu);
		return menuBar;
	}

	@Override
	public void setVisible(boolean newVisible) {
		if (newVisible == isVisible()) {
			L.debug("visible already '" + newVisible + '\'');
			return;
		}
		super.setVisible(newVisible);
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowClosed(WindowEvent e) {
		pluginManager.shutDown();
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}

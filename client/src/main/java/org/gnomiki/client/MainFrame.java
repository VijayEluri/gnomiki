package org.gnomiki.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.client.plugins.IPlugin;
import org.gnomiki.client.plugins.IPluginManager;
import org.gnomiki.client.plugins.IPluginView;
import org.gnomiki.core.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class MainFrame extends JFrame {
	
	private static final String [] REGION_ORDER= {BorderLayout.CENTER, BorderLayout.NORTH, BorderLayout.SOUTH, BorderLayout.WEST, BorderLayout.EAST} ; 

	@Autowired
	ResourceBundle bundle;
	private static final Log L = LogFactory.getLog(MainFrame.class);

	@Autowired
	private Configuration config;

	@Autowired
	private IPluginManager pluginManager;
	private HashMap<String, JTabbedPane> tabsMap;

	public MainFrame() {
		super("Gnomiki Client");
	}

	public void init() {
		setJMenuBar(composeMenuBar());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(800, 600));
		setLocationRelativeTo(null);
		configureContentPane();
		IPlugin[] plugins = pluginManager.getPlugins();
		for (IPlugin iPlugin : plugins) {
			IPluginView view = iPlugin.getView();
			if (view != null) {
				JTabbedPane pane = tabsMap.get(view.getPreferredRegion()) ; 
				pane.addTab(view.getTitle(), view.getComponent()) ; 
			}
		}
	}

	private void configureContentPane() {
		JPanel contentPane = new JPanel(new BorderLayout());
		tabsMap = new HashMap<String, JTabbedPane>();
		
		for (String key : REGION_ORDER) {
			
			JTabbedPane pane = new JTabbedPane();
			tabsMap.put(key, pane);
			contentPane.add(pane, key);
		}
		setContentPane(contentPane);

	}

	private JMenuBar composeMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu widgetMenu = new JMenu(bundle.getString("menu.label.widgets"));
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

}

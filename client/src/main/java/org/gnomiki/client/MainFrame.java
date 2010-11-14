package org.gnomiki.client;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.core.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class MainFrame extends JFrame {

	@Autowired
	ResourceBundle bundle;
	private static final Log L = LogFactory.getLog(MainFrame.class);

	@Autowired
	private Configuration config;

	public MainFrame() {
		super("Gnomiki Client");
	}

	public void init() {
		setJMenuBar(composeMenuBar());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(800, 600));

		setLocationRelativeTo(null);

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

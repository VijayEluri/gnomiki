package org.gnomiki.client;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.core.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class MainFrame extends JFrame {

	private static final Log L = LogFactory.getLog(MainFrame.class);

	@Autowired
	private Configuration config;

	public MainFrame() {
		super("Gnomiki Client");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void init() {
		setJMenuBar(composeMenuBar());

	}

	private JMenuBar composeMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new JMenu("Datei"));
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

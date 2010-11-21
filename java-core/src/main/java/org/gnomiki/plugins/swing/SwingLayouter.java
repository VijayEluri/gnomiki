package org.gnomiki.plugins.swing;

import java.awt.Dimension;

import javax.swing.JDialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.plugins.IPlugin;
import org.gnomiki.plugins.IPluginManager;

public class SwingLayouter implements IPlugin {

	private final int STACKER_OFFSET = 5;

	public static enum Region {

		/**
		 * A region to the lower side of the screen, spanning the whole screen.
		 */
		CONSOLE;
	}

	/**
	 * Resizes and positions a doalog so that it comes to place where specified.
	 * 
	 * @param dialog
	 * @param region
	 *            .
	 */
	public void layout(JDialog dialog, Region region) {

		switch (region) {
		case CONSOLE:
			layoutToConsole(dialog);
			break;
		}
	}

	private void layoutToConsole(JDialog dialog) {
		Dimension screenSize = dialog.getToolkit().getScreenSize();
		dialog.setSize(new Dimension((int) screenSize.getWidth(),
				(int) (screenSize.getHeight() / 4)));

		dialog.setLocation(0,
				(int) (screenSize.getHeight() - dialog.getHeight()));
	}

	private static final Log L = LogFactory.getLog(SwingLayouter.class);

	public static final String PLUGIN_ID = "swingLayout";

	private IPluginManager pluginManager;
	private Layouter layouter;

	public String getPluginId() {
		return PLUGIN_ID;
	}

	public void init(IPluginManager pluginManager) throws Exception {
		this.pluginManager = pluginManager;
		L.info("up and running");
	}

	public void shutDown() {
		L.info("shut down");

	}
}

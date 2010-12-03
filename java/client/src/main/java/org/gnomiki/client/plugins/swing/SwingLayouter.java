package org.gnomiki.client.plugins.swing;

import java.awt.Dimension;

import javax.swing.JDialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.exceptions.NotYetImplementedError;
import org.gnomiki.plugins.IPlugin;
import org.gnomiki.plugins.IPluginManager;

public class SwingLayouter implements IPlugin {

	public enum DialogType {

		/**
		 * A region to the right side of the screen.
		 */
		META,

		/**
		 * A region to the lower side of the screen, spanning the whole screen.
		 */
		CONSOLE;

	}

	private final int STACKER_OFFSET = 5;

	/**
	 * Resizes and positions a doalog so that it comes to place where specified.
	 * 
	 * @param dialog
	 * @param region
	 *            .
	 */
	public void layout(JDialog dialog, DialogType region) {

		switch (region) {
		case CONSOLE:
			layoutToConsole(dialog);
			break;

		case META:
			layoutToMeta(dialog);
			break;
		default:
			throw new NotYetImplementedError("layouting to region '" + region
					+ "'");
		}
	}

	private void layoutToMeta(JDialog dialog) {
		Dimension screenSize = dialog.getToolkit().getScreenSize();
		int width = (int) (screenSize.getWidth() / 4);
		int height = (int) (screenSize.getHeight() / 4);

		dialog.setSize(new Dimension(width, height));
		dialog.setLocation((int) (screenSize.getWidth() - width), 0);
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

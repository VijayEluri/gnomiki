package org.gnomiki.client.plugins;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;

/**
 * A view that can create a JPanel and deliver some view information.
 * 
 * @author MicWin
 * 
 */
public interface IPluginView {

	/**
	 * Returns a non-modal dialog representing this view.
	 * 
	 * @param parent
	 * @return
	 */
	public JDialog getDialog(JFrame parent);

	/**
	 * Returns a human readable Title for to be displayed in title bars and
	 * such.
	 * 
	 * @return
	 */
	public String getTitle();

	/**
	 * If this plugin provides Actions to be performed in the widgets Menu, it
	 * can do so by providing a JMenu.
	 * 
	 * @return
	 */
	public JMenu getMenu();

}

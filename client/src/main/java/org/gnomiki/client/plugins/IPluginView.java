package org.gnomiki.client.plugins;

import java.awt.BorderLayout;

import javax.swing.JComponent;

/**
 * A view that can create a JPanel and deliver some view information.
 * 
 * @author MicWin
 * 
 */
public interface IPluginView {

	public JComponent getComponent();

	/**
	 * Returns the preferred Region for the plugins view. One of BorderLayout:
	 * NORTH, WEST, SOUTH, EAST or CENTER.
	 * 
	 * @see BorderLayout
	 */
	public String getPreferredRegion();

	/**
	 * Returns a human readable Title for to be displayed in title bars and
	 * such.
	 * 
	 * @return
	 */
	public String getTitle();

}

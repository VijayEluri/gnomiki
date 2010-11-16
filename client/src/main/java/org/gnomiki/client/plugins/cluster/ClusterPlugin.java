package org.gnomiki.client.plugins.cluster;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;

import org.apache.commons.logging.Log;
import org.gnomiki.client.plugins.IPlugin;
import org.gnomiki.client.plugins.IPluginManager;
import org.gnomiki.client.plugins.IPluginView;
import org.gnomiki.client.plugins.console.ConsolePlugin;

public class ClusterPlugin implements IPlugin, IPluginView {

	public static final String PLUGIN_ID = "cluster";

	private ConsolePlugin con;

	private Log L;

	ClusterListener listener;

	public String getPluginId() {
		return PLUGIN_ID;
	}

	public IPluginView getView() {
		return this;
	}

	public void init(IPluginManager pluginManager) throws Exception {

		con = (ConsolePlugin) pluginManager.getPlugin(ConsolePlugin.PLUGIN_IN);
		L = con.getLog(ClusterPlugin.class);
		listener = new ClusterListener(con.getLog(ClusterListener.class), 100);

		new Thread(listener).start();
		L.info("initialized");
	}

	public void shutDown() {
		listener.shutDown();

	}

	@Override
	public JMenu getMenu() {
		// not yet providing any menu
		return null;
	}

	@Override
	public JDialog getDialog(JFrame parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		return "Cluster";
	}

}

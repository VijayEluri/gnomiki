package org.gnomiki.client.plugins.cluster;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.client.plugins.console.ConsolePlugin;
import org.gnomiki.cluster.Cluster;
import org.gnomiki.cluster.listener.ServerSocketListener;
import org.gnomiki.plugins.IPlugin;
import org.gnomiki.plugins.IPluginManager;

public class ClusterPlugin implements IPlugin {

	public static final String PLUGIN_ID = "cluster";

	private ConsolePlugin con;

	private final Log L = LogFactory.getLog(ClusterPlugin.class);

	ServerSocketListener listener;

	public String getPluginId() {
		return PLUGIN_ID;
	}

	public void init(IPluginManager pluginManager) throws Exception {

		con = (ConsolePlugin) pluginManager.getPlugin(ConsolePlugin.PLUGIN_ID);
		Cluster c = new Cluster();
		listener = new ServerSocketListener(5000, c);

		new Thread(listener).start();
		L.info("initialized");
	}

	public void shutDown() {
		listener.shutDown();

	}

	public JMenu getMenu() {
		return null;
	}

	public JDialog getDialog(JFrame parent) {
		return null;
	}

	public String getTitle() {
		return "Cluster";
	}

}

package org.gnomiki.client.plugins.cluster;

import org.apache.commons.logging.Log;
import org.gnomiki.client.plugins.IPlugin;
import org.gnomiki.client.plugins.IPluginManager;
import org.gnomiki.client.plugins.IPluginView;
import org.gnomiki.client.plugins.console.ConsolePlugin;

public class ClusterPlugin implements IPlugin {

	public static final String PLUGIN_ID = "cluster";

	private ConsolePlugin con;

	private Log L;

	public String getPluginId() {
		return PLUGIN_ID;
	}

	public IPluginView getView() {
		return null;
	}

	public void init(IPluginManager pluginManager) throws Exception {

		con = (ConsolePlugin) pluginManager.getPlugin(ConsolePlugin.PLUGIN_IN);
		L = con.getLog(ClusterPlugin.class);
		L.info("initialized");
	}

	public void shutDown() {

	}

}

package org.gnomiki.client.plugins.cluster;

import javax.swing.JDialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.cluster.Cluster;
import org.gnomiki.cluster.listener.ServerSocketListener;
import org.gnomiki.plugins.IPlugin;
import org.gnomiki.plugins.IPluginManager;
import org.gnomiki.plugins.swing.SwingLayouter;
import org.gnomiki.plugins.swing.SwingLayouter.DialogType;

public class ClusterPlugin implements IPlugin {

	public static final String PLUGIN_ID = "cluster";

	private final Log L = LogFactory.getLog(ClusterPlugin.class);

	ServerSocketListener listener;

	public String getPluginId() {
		return PLUGIN_ID;
	}

	public void init(IPluginManager pluginManager) throws Exception {

		SwingLayouter layouter = (SwingLayouter) pluginManager
				.getPlugin(SwingLayouter.PLUGIN_ID);

		layouter.layout(getDialog(), DialogType.META);
		Cluster c = new Cluster();
		listener = new ServerSocketListener(5000, c);

		new Thread(listener).start();

		L.info("initialized");
	}

	private JDialog getDialog() {
		JDialog dialog = new JDialog();
		dialog.setTitle(getTitle());
		dialog.setVisible(true);
		return dialog;
	}

	public void shutDown() {
		listener.shutDown();

	}

	public String getTitle() {
		return "Cluster";
	}

}

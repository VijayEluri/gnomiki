package org.gnomiki.client.plugins.console;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.client.plugins.IPlugin;
import org.gnomiki.client.plugins.IPluginManager;
import org.gnomiki.client.plugins.IPluginView;
import org.gnomiki.client.plugins.cluster.ClusterPlugin;
import org.gnomiki.core.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Plugin that puts out log output done via commons logging to a view.
 * 
 * @author MicWin
 * 
 */
public class ConsolePlugin implements IPlugin {

	public static final String PLUGIN_IN = "guiLog";

	private Log L = LogFactory.getLog(ConsolePlugin.class);

	@Autowired
	private Configuration config;

	private ConsolePanel consolePanel;

	private IPluginManager pluginManager;

	public String getPluginId() {
		return PLUGIN_IN;
	}

	public void init(IPluginManager pm) throws Exception {
		this.pluginManager = pm;
		L.info("initialized");
	}

	public void shutDown() {
		L.info("shutdown");
	}

	public IPluginView getView() {
		return getConsolePanel();
	}

	private ConsolePanel getConsolePanel() {
		if (consolePanel == null) {
			consolePanel = new ConsolePanel();
		}
		return consolePanel;
	}

	public void info(Class clazz, String msg) {
		LogFactory.getLog(clazz).info(msg);
	}

	public Log getLog(Class clazz) {
		return new ConsoleLog(clazz, getConsolePanel().getTable());
	}
}

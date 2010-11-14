package org.gnomiki.client.plugins;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.core.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfigurationPluginManager implements IPluginManager {
	
	

	private Log L = LogFactory.getLog(ConfigurationPluginManager.class);
	
	@Autowired
	Configuration config;

	Map<String, IPlugin> pluginsById = new HashMap<String, IPlugin>();

	public IPlugin getPlugin(String pluginId) {
		return pluginsById.get(pluginId);
	}

	public void init() {
		String[] pluginClasses = config.getStringArray("plugins");
		for (int i = 0; i < pluginClasses.length; i++) {
			try {
				Class clazz = Class.forName(pluginClasses[i]);
				IPlugin plugin = (IPlugin) clazz.newInstance();
				plugin.init(this);
				pluginsById.put(plugin.getPluginId(), plugin);
				L.info("registered Plugin '" + plugin.getPluginId() + "'");
			} catch (Exception e) {
				L.error("Cannot laod plugin class '" + pluginClasses[i] + "'",
						e);
			}
		}
	}

	public IPlugin[] getPlugins() {
		return pluginsById.values().toArray(new IPlugin[0]);
	}
}

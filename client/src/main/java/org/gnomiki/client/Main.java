package org.gnomiki.client;

import org.gnomiki.plugins.IPlugin;
import org.gnomiki.plugins.IPluginManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 * 
 */
public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"app-context.xml");

		IPluginManager pluginManager = (IPluginManager) ctx
				.getBean("pluginManager");
		for (IPlugin plugin : pluginManager.getPlugins()) {
			System.out.println(plugin.getPluginId());
		}
		System.out.println(pluginManager);
	}
}

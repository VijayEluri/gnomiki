package org.gnomiki.core.config.impl;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gnomiki.core.config.Configuration;

/**
 * Implements a configuration that configures from System Properties.
 * 
 * @author A29300
 * 
 */
public class PropertiesConfigImpl implements Configuration {
	private static Log L = LogFactory.getLog(PropertiesConfigImpl.class);
	private final Properties props;

	public PropertiesConfigImpl() {
		this(System.getProperties());
	}

	public PropertiesConfigImpl(Properties props) {
		this.props = props;
		L.info("instantiated.");
	}

	public String getString(String key) {
		return null;
	}

}

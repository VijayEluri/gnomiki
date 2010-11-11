package org.gnomiki.core.config;


/**
 * A configuration class interface dynamically loaded on startup.
 * 
 * @author micwin
 * 
 */
public interface Configuration {

	/**
	 * Retrieves a value as a String.
	 * 
	 * @param key
	 * @return The value, <code>null</code> if not set.
	 */
	String getString(String key);

}

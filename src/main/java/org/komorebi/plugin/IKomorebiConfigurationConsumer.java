package org.komorebi.plugin;

/**
 * Any PlugIn that needs configuration settings stored or retrieved must implement this interface.
 * By calling {@link #setConfigurationValue(String, String) setConfigurationvalue(...)} the Komorebi
 * core will pass all configuration values to the PlugIn.
 *  
 * @author lycis
 *
 */
public interface IKomorebiConfigurationConsumer extends IKomorebiPlugin{
	/**
	 * This method is called by the core to publish the values set in the configuration file that
	 * that are registered for the PlugIn.
	 * @param key
	 * @param value
	 */
	public void setConfigurationValue(String key, String value);
	
	/**
	 * The key returned by this method will be used to identify the configuration values that are
	 * required by this PlugIn. A common value to return would be the name of the PlugIn.
	 * 
	 * The values that will be passed have to places under the <code>plugin<code> node.
	 * @return
	 */
	public String getPluginConfigurationKey();
}

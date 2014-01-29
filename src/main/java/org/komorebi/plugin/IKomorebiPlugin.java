package org.komorebi.plugin;

/**
 * Base interface for all PlugIn types.
 * 
 * @author lycis
 *
 */
public interface IKomorebiPlugin {
	/**
	 * Identifies this PlugIn as configuration consumer and causes the core to publish
	 * configuration values by access through the interface IKomorebiConfigurationConsumer.
	 * 
	 * A PlugIn that returns <code>true</code> on this method has to implement
	 * IKomorebiConfigurationConsumer.
	 */
	boolean isConfigConsumer();
}

package org.komorebi.plugin.storage;

import java.util.List;

import org.komorebi.plugin.IKomorebiPlugin;

/**
 * A PlugIn of this type provides an algorithm that calculates which data of
 * a file will be stored at which location.
 * 
 * @author lycis
 *
 */
public interface IKomorebiStoreAlgorithm extends IKomorebiPlugin {
	
	/**
	 * Calculates how the data of a file is scattered across the available
	 * storage locations of that user. The PlugIn has to populate and return
	 * the meta data for the file. Only the data block information is relevant
	 * and these information have to be supplied by the PlugIn (everything
	 * else will be ignored):
	 * <ol>
	 *   <li>Location (storage location)</li>
	 *   <li>Size (length of the data block)</li>
	 * </ol>
	 * 
	 * @param locations names of available locations
	 * @param data the data of the file
	 * @return meta information of the file
	 */
	public KomorebiFileInfo calculate(List<String> locations, byte[] data);
}

package org.komorebi.plugin;

/**
 * This interface defines a storage location for Komorebi. This is any place where it may place chunks
 * of the files that are stored in the system.
 * 
 * @author lycis
 *
 */
public interface IKomorebiStorage {
	/**
	 * Stores a chunk of data in the given partition block.
	 * If the chunk could not be stored to the storage location for any reason the status of all
	 * partitions has to remain unchanged!
	 * 
	 * @param partition gives the number of the partition block that the chunk will be stored in
	 * @param chunk data to be stored
	 * @return the position within the partition block that the chunk was stored in
	 */
	public long storeChunk(int partition, byte[] chunk);
	
	/**
	 * Returns any previously stored data chunk.
	 * This method must never change the partition that is accessed.
	 * 
	 * @param partition the number of the partition
	 * @param position position of the chunk within the partition
	 * @param chunk the buffer that will store the chunk
	 * @return
	 */
	public long readChunk(int partition, long position, byte[] chunk);
}

package org.komorebi.plugin.storage;

import java.util.List;

/**
 * This class contains partitioning information about a file. It does not
 * contain any real data but merely tells which bytes of a file are stored at
 * which location. This is the same information that is stored at the 
 * partition index of Komorebi. Each file has exactly one of these metadata
 * headers.
 * 
 * Most of this information is maintained by the storage manager itself.
 * 
 * @author lycis
 *
 */
public class KomorebiFileInfo {
	private int blockCount; // number of data blocks
	private List<DataBlock> blocks; // information about each data block
	private String path; // absolute path (including name) of the file
	
	/**
	 * Provides the length of the file. This value is dynamically calculated by
	 * summing up the length of all data blocks the file consists of.
	 * 
	 * @return length of the file
	 */
	public long getSize(){
		long length = 0;
		
		for(DataBlock d: blocks){
			length += d.getSize();
		}
		
		return length;
	}
	
	/**
	 * Sets the absolute path of the file including the file name. Every path
	 * of the Komorebi Storage engine has to be in the "unix path format" 
	 * (containing foreward slashes and no spaces).
	 * 
	 * @param path
	 */
	public void setPath(String path){
		this.path = path;
	}
	
	/**
	 * @return absolute path of the file
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * Returns the metadata of the data block with the given number.
	 * 
	 * @param number number of the data block to access
	 * @return data block meta information
	 */
	public DataBlock getBlock(int number){
		if(number > blocks.size() || number < 0){
			return null;
		}
		return blocks.get(number);
	}
	
	/**
	 * Returns the number of blocks that make up the file.
	 * @return number of blocks
	 */
	public int getBlockCount(){
		return blocks.size();
	}
	
	/**
	 * Appends information about a data block to the file record. This
	 * is most likely done by a storage algorithm PlugIn.
	 * 
	 * @param db data block meta information
	 */
	public void appendBlock(DataBlock db){
		blocks.add(db);
	}
	
	/**
	 * Contains information about a single data block of a file.
	 * @author lycis
	 *
	 */
	public class DataBlock{
		private String location; // storage location that contains the data block
		private long partition; // storage partition that the data block is stored in within a location
		private long position; // position of the data block within the partition
		private long size; // size of the data block in bytes
		
		/**
		 * @return storage location of the data blcok
		 */
		public String getLocation() {
			return location;
		}
		
		/**
		 * Sets the associated storage location. This is usually done by the storage 
		 * algorithm except during a reorganisation of the store (done when changing
		 * storage algorithms).
		 * 
		 * @param location name of the storage location
		 */
		public void setLocation(String location) {
			this.location = location;
		}
		
		/**
		 * @return partition within the storage location
		 */
		public long getPartition() {
			return partition;
		}
		
		/**
		 * Sets the partition of the data block within the storage location. This
		 * is always managed by the Storage Manager so PlugIns are not involved and
		 * should not set this.
		 * 
		 * @param partition number of the partition
		 */
		public void setPartition(long partition) {
			this.partition = partition;
		}
		
		/**
		 * @return position within the data partition
		 */
		public long getPosition() {
			return position;
		}
		
		/**
		 * Changes the position of the data block within a partition. This setting
		 * is always managed by the Storage Manager and never involves any PlugIn.
		 * 
		 * @param position byte position within the storage partition
		 */
		public void setPosition(long position) {
			this.position = position;
		}
		
		/**
		 * @return size of the data block
		 */
		public long getSize() {
			return size;
		}
		
		/**
		 * Gives the size of the data block. This setting is always managed by the
		 * storage algorithm PlugIn that splits a file into data blocks.
		 * 
		 * @param size length of the data block
		 */
		public void setSize(long size) {
			this.size = size;
		}
	}
}

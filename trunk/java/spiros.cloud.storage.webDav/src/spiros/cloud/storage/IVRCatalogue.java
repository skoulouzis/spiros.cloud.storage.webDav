package spiros.cloud.storage;

import spiros.cloud.storage.resources.IResourceEntry;

public interface IVRCatalogue {

	public void registerResourceEntry(IResourceEntry entry) throws Exception;

	public IResourceEntry getResourceEntryByLRN(String logicalResourceName)
			throws Exception;
	
	public IResourceEntry getResourceEntryByUID(String UID)
	throws Exception;

	public void unregisterResourceEntry(IResourceEntry entry) throws Exception;

	public Boolean resourceEntryExists(IResourceEntry entry) throws Exception;;

	public IResourceEntry getRoot() throws Exception;

}

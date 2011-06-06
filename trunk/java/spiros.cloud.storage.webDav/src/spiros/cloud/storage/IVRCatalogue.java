package spiros.cloud.storage;

import java.util.ArrayList;

import spiros.cloud.storage.resources.IResourceEntry;

public interface IVRCatalogue {

	public void registerResourceEntry(IResourceEntry entry)throws Exception;

	public IResourceEntry getResourceEntry(String logicalResourceName)throws Exception;

	public void unregisterResourceEntry(IResourceEntry entry)throws Exception;

	public Boolean resourceEntryExists(IResourceEntry entry)throws Exception;;

	public ArrayList<IResourceEntry> getTopLevelResourceEntries()throws Exception;;

}

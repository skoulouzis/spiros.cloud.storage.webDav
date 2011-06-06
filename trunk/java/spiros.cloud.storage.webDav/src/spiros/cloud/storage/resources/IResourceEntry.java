package spiros.cloud.storage.resources;

import java.util.ArrayList;


public interface IResourceEntry {
	
	public String getLRN();
	
	public Metadata getMetadata();
	
	public void setMetadata(Metadata metadata);

	public String getUID();
	
	public void setUID(String UID);
}

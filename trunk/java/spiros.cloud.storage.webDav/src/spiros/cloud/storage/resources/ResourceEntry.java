package spiros.cloud.storage.resources;

import java.io.Serializable;


public abstract class ResourceEntry implements IResourceEntry, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1529997963561059214L;
	private String lrn;
	private Metadata metadata;

	public ResourceEntry(String logicalResourceName) {
		this.lrn = logicalResourceName;
	}
	
	@Override
	public String getLRN() {
		return lrn;
	}

	@Override
	public Metadata getMetadata() {
		return metadata;
	}
	
	@Override
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}

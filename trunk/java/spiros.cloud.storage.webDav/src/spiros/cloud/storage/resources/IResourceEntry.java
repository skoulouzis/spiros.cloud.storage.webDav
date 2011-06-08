package spiros.cloud.storage.resources;


public interface IResourceEntry {

	public String getLRN();

	public Metadata getMetadata();

	public void setMetadata(Metadata metadata);

	public String getUID();
}

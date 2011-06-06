package spiros.cloud.storage.resources;

import java.util.ArrayList;
import java.util.List;


public class ResourceFileEntry extends ResourceEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6019657958412257303L;
	private List<AccessLocation> accessLocations;
	private String UID;

	public ResourceFileEntry(String logicalResourceName) {
		super(logicalResourceName);
	}

	public void addAccessLocations(List<AccessLocation> accessLocations) {
		if (this.accessLocations == null) {
			this.accessLocations = new ArrayList<AccessLocation>();
		}
		this.accessLocations.addAll(accessLocations);
	}

	public void removeAccessLocations(List<AccessLocation> accessLocations) {
		if (this.accessLocations != null && !this.accessLocations.isEmpty()) {
			this.accessLocations.removeAll(accessLocations);
		}
	}

	public void removeAllAccessLocations() {
		if (this.accessLocations != null && !this.accessLocations.isEmpty()) {
			this.accessLocations.clear();
		}
	}

	public List<AccessLocation> getAccessLocations() {
		return accessLocations;
	}

	public void addAccessLocation(AccessLocation accessLocation) {
		if (this.accessLocations == null) {
			this.accessLocations = new ArrayList<AccessLocation>();
		}
		this.accessLocations.add(accessLocation);
	}

	@Override
	public String getUID() {
		return this.UID;
	}
	
	@Override
	public void setUID(String UID) {
		this.UID = UID;
	}

}

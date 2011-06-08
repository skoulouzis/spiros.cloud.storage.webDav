package spiros.cloud.storage.resources;

import java.io.Serializable;

public class AccessLocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2552461454620784560L;
	private String[] locations;

	public AccessLocation(String[] uriString) {
		this.setLocations(uriString);
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	public String[] getLocations() {
		return locations;
	}

}

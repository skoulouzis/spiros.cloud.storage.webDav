package spiros.cloud.storage.resources;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

public class ResourceEntry implements IResourceEntry, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1529997963561059214L;
	private String UID = null;
	private String lrn;
	private Metadata metadata;

	public ResourceEntry(String logicalResourceName) throws IOException {
		setLRN(logicalResourceName);
		if (this.UID == null) {
			this.UID = java.util.UUID.randomUUID().toString();
		}
	}

	private String removeLastChar(String logicalResourceName) {

		if (logicalResourceName == null) {
			return null;
		}
		int strLen = logicalResourceName.length();
		if (strLen < 2) {
			return "";
		}
		int lastIdx = strLen - 1;
		String ret = logicalResourceName.substring(0, lastIdx);
		char last = logicalResourceName.charAt(lastIdx);
		if (last == '\n') {
			if (ret.charAt(lastIdx - 1) == '\r') {
				return ret.substring(0, lastIdx - 1);
			}
		}
		return ret;
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

	@Override
	public String getUID() {
		return this.UID;
	}

	private void debug(String msg) {
		System.err.println(this.getClass().getSimpleName() + ": " + msg);
	}

	protected void setLRN(String newLRN) {
		String formated = null;
		String tmp = newLRN;
		if (newLRN.equals("/")) {
			this.lrn = newLRN;
		} else {
			if (newLRN.endsWith("/")) {
				tmp = removeLastChar(newLRN);
			}
			try {
				URI uri = new URI("file://" + tmp);
				formated = uri.getPath();
				tmp = formated.replaceAll("//", "/");
				formated = tmp;

				// debug("Formated: "+formated);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.lrn = formated;
		}
	}
}

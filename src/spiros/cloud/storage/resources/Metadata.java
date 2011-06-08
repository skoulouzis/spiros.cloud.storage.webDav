package spiros.cloud.storage.resources;

import java.io.Serializable;

public class Metadata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7617145607817495167L;
	private Long createDate = null;
	private Long modifiedDate;
	private Long length;
	private String mimeType;

	public Long getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Long modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getLength() {
		return this.length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}

package spiros.cloud.storage.resources;

import java.io.Serializable;
import java.util.Date;

public class Metadata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7617145607817495167L;
	private Long createDate = null;
	private Long modifiedDate;
	private Long length;

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

}

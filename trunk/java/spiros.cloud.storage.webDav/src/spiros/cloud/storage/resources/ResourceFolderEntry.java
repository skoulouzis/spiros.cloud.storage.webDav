package spiros.cloud.storage.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceFolderEntry extends ResourceEntry {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2765285570381475790L;
	private List<ResourceEntry> children;

	public ResourceFolderEntry(String logicalResourceName) throws IOException {
		super(logicalResourceName);
	}

	public List<ResourceEntry> getChildren() {
		return this.children;
	}

	public void addChildren(List<ResourceEntry> children) {
		if (this.children == null) {
			this.children = new ArrayList<ResourceEntry>();
		}
		this.children.addAll(children);
	}

	public void removeChildren(List<IResourceEntry> children) {
		if (this.children != null && !this.children.isEmpty()) {
			this.children.removeAll(children);
		}
	}

	public void removeAllChildren() {
		if (this.children != null && !this.children.isEmpty()) {
			this.children.clear();
		}
	}

	public void addChild(ResourceEntry child) throws Exception {
		if (this.children == null) {
			this.children = new ArrayList<ResourceEntry>();
		}
		String resolvedLRN = resolveChildName(child.getLRN());
		child.setLRN(resolvedLRN);
		this.children.add(child);
	}

	private String resolveChildName(String lrn) throws Exception {
		String theChildName;
		String parentLRN ;
		
		String[] parts = lrn.split("/");
		if(parts!=null && parts.length >=1 ){
			theChildName = parts[parts.length-1];
			int beginIndex = 0;
			int endIndex = (lrn.length() - theChildName.length())-1;
			parentLRN = lrn.substring(beginIndex, endIndex);
			if(parentLRN.endsWith("/")){
				parentLRN = parentLRN.substring(parentLRN.length()-2, parentLRN.length()-1);
			}
			if(!parentLRN.equals(this.getLRN())){
				throw new Exception("Child path is wrong. Added child's parent is: "+parentLRN+" while parent should be: "+getLRN() );
			}
			return lrn;
		}else{
			return getLRN()+"/"+lrn;
		}
	}

	public IResourceEntry getChild(String name) {
		for (IResourceEntry r : children) {
			if (r.getLRN().equals(name)) {
				return r;
			}
		}
		return null;
	}

	public Boolean hasChildren() {
		if (this.children == null || this.children.isEmpty()
				|| children.size() <= 0) {
			return false;
		}
		return true;
	}

}

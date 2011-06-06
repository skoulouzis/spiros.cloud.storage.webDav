package spiros.cloud.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import spiros.cloud.storage.resources.IResourceEntry;
import spiros.cloud.storage.resources.ResourceEntry;

import nl.uva.vlet.GlobalConfig;
import nl.uva.vlet.GlobalUtil;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.vrs.VComposite;
import nl.uva.vlet.vrs.VNode;
import nl.uva.vlet.vrs.VRS;
import nl.uva.vlet.vrs.VRSClient;
import nl.uva.vlet.vrs.VRSContext;

public class SimpleVRCatalogue implements IVRCatalogue {

	 private File db;
	//
	public SimpleVRCatalogue() throws MalformedURLException, URISyntaxException {
		this.db = new File(new URI(Config.DB_LOC_URI));
	}
	
	public void deleteAllEntries() {
		File[] data = db.listFiles();
		for (int i = 0; i < data.length; i++) {
			data[i].delete();
		}
	}

	public String getDBLocation() {
		return this.db.getAbsolutePath();
	}



	private void debug(String msg) {
		System.err.println(this.getClass().getSimpleName() + ": " + msg);
	}

	@Override
	public void registerResourceEntry(IResourceEntry entry)
			throws URISyntaxException, IOException {
		saveEntry(entry);
	}

	private void saveEntry(IResourceEntry entry) throws URISyntaxException,
			IOException {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		String fileName = logicalResourceName2FileName(entry.getLRN());

		fos = new FileOutputStream(new File(new URI(Config.DB_LOC_URI
				+ File.separator + fileName)));
		out = new ObjectOutputStream(fos);
		out.writeObject(entry);
		out.close();
	}

	private String logicalResourceName2FileName(String logicalPath) {
		return logicalPath.replaceAll("/", "");
	}

	@Override
	public IResourceEntry getResourceEntry(String logicalResourceName)
			throws IOException, ClassNotFoundException {
		String fName = logicalResourceName2FileName(logicalResourceName);

		File[] data = db.listFiles();

		for (int i = 0; i < data.length; i++) {
			if (data[i].getName().equals(fName)) {
				return loadNodeEntry(data[i]);
			}
		}
		return null;
	}

	@Override
	public void unregisterResourceEntry(IResourceEntry entry) {
		String fName = logicalResourceName2FileName(entry.getLRN());

		File[] data = db.listFiles();

		for (int i = 0; i < data.length; i++) {
			if (data[i].getName().equals(fName)) {
				data[i].delete();
			}
		}
	}

	@Override
	public Boolean resourceEntryExists(IResourceEntry entry) {
		String fName = logicalResourceName2FileName(entry.getLRN());

		File[] data = db.listFiles();

		for (int i = 0; i < data.length; i++) {
			if (data[i].getName().equals(fName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<IResourceEntry> getTopLevelResourceEntries()
			throws IOException, ClassNotFoundException {

		File[] data = db.listFiles();
		IResourceEntry entry;
		ArrayList<IResourceEntry> topLevelEntries = new ArrayList<IResourceEntry>();
		for (int i = 0; i < data.length; i++) {
			entry = loadNodeEntry(data[i]);
			String[] parts = entry.getLRN().split(File.separator);
			if (parts.length == 1) {
				topLevelEntries.add(entry);
			}
		}

		return topLevelEntries;
	}

	private IResourceEntry loadNodeEntry(String fName) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(fName);
		ObjectInputStream in = new ObjectInputStream(fis);
		return (IResourceEntry) in.readObject();
	}

	private IResourceEntry loadNodeEntry(File file) throws IOException,
			ClassNotFoundException {
		return loadNodeEntry(file.getAbsolutePath());
	}
}

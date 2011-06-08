package spiros.cloud.storage.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import spiros.cloud.storage.resources.IResourceEntry;
import spiros.cloud.storage.resources.Metadata;
import spiros.cloud.storage.resources.ResourceEntry;
import spiros.cloud.storage.resources.ResourceFolderEntry;

public class TestResourceEntry extends spiros.cloud.storage.test.Test {
	private String name = "resource";
	private String path = "/dummy/path/to/" + name;

	private String[] testPaths = { "/" + path, 
			path + "/" , 
			"/" + path + "/" };

	private ResourceEntry[] resources;

	public TestResourceEntry() throws Exception {
		super();
		resources = new ResourceEntry[testPaths.length];
		for (int i = 0; i < testPaths.length; i++) {
			resources[i] = new ResourceEntry(testPaths[i]);
		}

	}

	@Test
	public void testResourceEntry() throws IOException {
		for (int i = 0; i < testPaths.length; i++) {
			assertNotNull(resources[i]);
		}

	}

	@Test
	public void testGetLRN() throws IOException {
		for (int i = 0; i < testPaths.length; i++) {
			String lrn = resources[i].getLRN();
			// debug("LRN: " + lrn + ". Name:" + path);
			assertEquals("Failed at path :"+testPaths[i]+". index: "+i,path, lrn);
		}
		
		String lrn = "aDir";
		ResourceEntry newDir = new ResourceEntry(lrn);
		assertEquals(lrn, newDir.getLRN());
	}

	@Test
	public void testMetadata() throws IOException {
		for (int i = 0; i < testPaths.length; i++) {
			Metadata meta1 = new Metadata();
			resources[i].setMetadata(meta1);

			Metadata meta2 = resources[i].getMetadata();
			assertEquals(meta2, meta1);
		}
		
		ResourceEntry newDir = new ResourceEntry("aDir");
		
		Metadata meta = newDir.getMetadata();
		assertNotNull(meta);

		Long create = meta.getCreateDate();

		assertNotNull(create);
	}

	@Test
	public void testGetUID() {
		for (int i = 0; i < testPaths.length; i++) {
			assertNotNull(resources[i].getUID());
		}
	}

	private void debug(String msg) {
		System.err.println(this.getClass().getSimpleName() + ": " + msg);
	}
}

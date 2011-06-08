/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import nl.uva.vlet.vfs.VFile;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import spiros.cloud.storage.SimpleVRCatalogue;
import spiros.cloud.storage.resources.IResourceEntry;
import spiros.cloud.storage.resources.ResourceEntry;
import spiros.cloud.storage.resources.ResourceFolderEntry;

/**
 * 
 * @author S. Koulouzis
 */
public class VirtualFileCatalogueTest extends spiros.cloud.storage.test.Test {

	private static int fileCount;

	public VirtualFileCatalogueTest() throws Exception {
		super();
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() throws URISyntaxException, IOException {

	}

	@After
	public void tearDown() {
	}

	@Test
	public void testUIDs() throws MalformedURLException, URISyntaxException {
		for (ResourceEntry entry : getAllEntries()) {
			if (entry.getUID() == null || entry.getUID().equals("")) {
				fail("Entry " + entry.getLRN() + " has null or empty UID: "
						+ entry.getUID());
			}
		}
		
		boolean duplicates = hasDuplicates(getAllEntries());
		assertFalse(duplicates);
	}

	@Test
	public void testLoadAll() throws URISyntaxException, IOException,
			ClassNotFoundException {
		SimpleVRCatalogue instance = new SimpleVRCatalogue();
		List<ResourceEntry> loadedAll = instance.loadAllEntries();
		
		boolean duplicates = hasDuplicates(loadedAll);
		assertFalse(duplicates);

		boolean matsch = compareResourceEntryList(getAllEntries(),loadedAll);
		assertTrue(matsch);
		
	}

	@Test
	public void testExists() throws URISyntaxException, IOException,
			ClassNotFoundException {
		SimpleVRCatalogue instance = new SimpleVRCatalogue();
		for (ResourceEntry entry : getAllEntries()) {
			assertTrue(instance.resourceEntryExists(entry));
		}
	}

	@Test
	public void testgetTopEntries() throws URISyntaxException, IOException,
			ClassNotFoundException {
		SimpleVRCatalogue instance = new SimpleVRCatalogue();
		List<ResourceEntry> rootCh = instance.getRoot().getChildren();

		boolean match = compareResourceEntryList(rootCh, getTopEntries());
		assertTrue(match);
		
		 match = compareResourceEntryList(instance.getTopLevelResourceEntries(), getTopEntries());
		 assertTrue(match);

	}
	
	@Test
	public void testRegisterChild() throws Exception{
//		SimpleVRCatalogue cat = new SimpleVRCatalogue();
		
//		ResourceFolderEntry theDir = (ResourceFolderEntry)cat.getResourceEntryByUID(getTestDirUID());
//		ResourceEntry child = new ResourceEntry("child");
//		theDir.addChild(child);
	}
	
	@Test
	public void testCreateAndDeleteFile() throws Exception{
//		SimpleVRCatalogue cat = new SimpleVRCatalogue();
//		
//		ResourceFolderEntry theDir = (ResourceFolderEntry) cat.getResourceEntryByUID(getTestDirUID());
//		ResourceEntry child = new ResourceEntry("child");
//		theDir.addChild(child);
//		
//		cat.registerResourceEntry(theDir);
		
//        VFile newFile = getRemoteTestDir().createFile(nextFilename("testFile"));
//        // sftp created 1 byte length new files !
//        Assert.assertNotNull("New created file may not be NULL", newFile);
//        Assert.assertTrue("Length of newly created file must be 0!:" + getRemoteTestDir(), newFile.getLength() == 0);
//        newFile.delete();
		
	}

	private void debug(String msg) {
		System.err.println(this.getClass().getSimpleName() + ": " + msg);
	}

	// private static void listAllNodes(File cloudFiles) {
	// File[] list = cloudFiles.listFiles();
	// for (File f : list) {
	// fileCount++;
	// // debug("Files: "+f.getAbsolutePath());
	// if (f.isDirectory()) {
	// listAllNodes(f);
	// }
	// }
	// }
	//
	// /**
	// * Test of registerNodeEntry method, of class VirtualFileCatalogue.
	// */
	// @Test
	// public void testRegisterNodeEntry() throws Exception {
	// System.out.println("registerNodeEntry");
	// IResourceEntry entry = new ResourceFileEntry("entry");
	// SimpleVRCatalogue instance = new SimpleVRCatalogue();
	// instance.registerResourceEntry(entry);
	//
	// boolean exists = instance.resourceEntryExists(entry);
	// assertTrue(exists);
	//
	// instance.unregisterResourceEntry(entry);
	//
	// }
	//
	// /**
	// * Test of deleteNodeEntry method, of class VirtualFileCatalogue.
	// *
	// * @throws MalformedURLException
	// * @throws URISyntaxException
	// */
	// @Test
	// public void testDeleteNodeEntry() throws MalformedURLException,
	// URISyntaxException {
	// System.out.println("deleteNodeEntry");
	// IResourceEntry entry = new ResourceFolderEntry("entry");
	// SimpleVRCatalogue instance = new SimpleVRCatalogue();
	// instance.unregisterResourceEntry(entry);
	// boolean exists = instance.resourceEntryExists(entry);
	//
	// assertFalse(exists);
	// }
	//
	// /**
	// * Test of getTopLevelEntries method, of class VirtualFileCatalogue.
	// */
	// @Test
	// public void testGetTopLevelEntries() throws Exception {
	// System.out.println("getTopLevelEntries");
	//
	// Util.initTestCatalouge();
	//
	// SimpleVRCatalogue instance = new SimpleVRCatalogue();
	//
	// ArrayList<IResourceEntry> result = instance
	// .getTopLevelResourceEntries();
	// fileCount = 0;
	// for (int i = 0; i < Config.CLOUD_LOCATIONS_URI.length; i++) {
	// File f = new File(new URI(Config.CLOUD_LOCATIONS_URI[i]));
	// for (File file : f.listFiles()) {
	// if (!file.getName().startsWith(".")) {
	// fileCount++;
	// }
	// }
	// }
	// assertEquals(fileCount, result.size());
	// }
}

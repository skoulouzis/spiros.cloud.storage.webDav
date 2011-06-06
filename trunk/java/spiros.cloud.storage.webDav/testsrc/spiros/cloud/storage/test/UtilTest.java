/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import nl.uva.vlet.exception.VlException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import spiros.cloud.storage.Config;
import spiros.cloud.storage.Util;
import spiros.cloud.storage.resources.IResourceEntry;

import static org.junit.Assert.*;

/**
 * 
 * @author S. Koulouzis
 */
public class UtilTest {

	private static int fileCount;

	public UtilTest() {

	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testTestLocations() throws URISyntaxException {

		File f = new File(new URI(Config.TEST_DATA_URI));
		assertTrue(f.exists());

		for (String s : Config.CLOUD_LOCATIONS_URI) {
			f = new File(new URI(s));
			if (!f.exists()) {
				fail("Could not find files in " + f.getAbsolutePath());
			}
			assertTrue(f.exists());
		}

		f = new File(new URI(Config.DB_LOC_URI));
		assertTrue(f.exists());

	}

	@Test
	public void testGetLogicalName() throws URISyntaxException {
		File f = new File(new URI(Config.CLOUD_LOC1_URI));
		String[] fNames = new String[] { "newFile1", ".dotName" };
		String dirName = "L1Dir";

		for (int i = 0; i < fNames.length; i++) {
			File aFile = new File(f.getAbsolutePath() + File.separator
					+ dirName + File.separator + fNames[i]);
			// message("Base URI: "+Config.CLOUD_LOC1_URI);
			// message("Absolute URI: "+aFile.getAbsolutePath());
			String lrn = Util.getLogicalName(Config.CLOUD_LOC1_URI,
					aFile.getAbsolutePath());
			// message("LRN: "+lrn);
			String expectedName = dirName + File.separator + fNames[i];
			assertEquals(expectedName, lrn);
		}

		for (int i = 0; i < fNames.length; i++) {
			File aFile = new File(new URI("file://" + f.getAbsolutePath()
					+ File.separator + dirName + File.separator + fNames[i]));
			// message("Base URI: "+Config.CLOUD_LOC1_URI);
			// message("Absolute URI: "+aFile.getAbsolutePath());
			String lrn = Util.getLogicalName(Config.CLOUD_LOC1_URI,
					aFile.getAbsolutePath());
			// message("LRN: "+lrn);
			String expectedName = dirName + File.separator + fNames[i];
			assertEquals(expectedName, lrn);
		}
	}

	private void message(String msg) {
		System.err.println(this.getClass().getSimpleName() + ": " + msg);
	}

	/**
	 * Test of initTestCatalouge method, of class Util.
	 * 
	 * @throws VlException
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void testInitTestCatalouge() throws VlException, URISyntaxException,
			IOException {
		System.out.println("initTestCatalouge");

		deleteAllDBFiles();

		Util.initTestCatalouge();
		File f = new File(new URI(Config.DB_LOC_URI));
		assertTrue(f.exists());
		fileCount = 0;
		listAllNodes(f);
		int dbFiles = fileCount;

		fileCount = 0;
		for (String s : Config.CLOUD_LOCATIONS_URI) {
			f = new File(new URI(s));
			assertTrue(f.exists());

			listAllNodes(f);
		}
		int files = fileCount;
		assertEquals(files, dbFiles);
	}

	private void deleteAllDBFiles() throws URISyntaxException {
		File f = new File(new URI(Config.DB_LOC_URI));

		assertTrue(f.exists());

		File[] dbFiles = f.listFiles();

		for (File dbF : dbFiles) {
			dbF.delete();
		}

	}

	private static void listAllNodes(File cloudFiles) {
		if (!cloudFiles.getName().startsWith(".")) {
			File[] list = cloudFiles.listFiles();
			for (File f : list) {
				fileCount++;
				// debug("Files: "+f.getAbsolutePath());
				if (f.isDirectory()) {
					listAllNodes(f);
				}
			}
		}
	}
}

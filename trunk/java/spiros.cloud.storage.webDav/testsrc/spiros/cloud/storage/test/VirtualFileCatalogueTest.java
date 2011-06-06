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
import nl.uva.vlet.vrms.ResourceFolder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import spiros.cloud.storage.Config;
import spiros.cloud.storage.SimpleVRCatalogue;
import spiros.cloud.storage.Util;
import spiros.cloud.storage.resources.IResourceEntry;
import spiros.cloud.storage.resources.ResourceEntry;
import spiros.cloud.storage.resources.ResourceFileEntry;
import spiros.cloud.storage.resources.ResourceFolderEntry;
import static org.junit.Assert.*;

/**
 *
 * @author S. Koulouzis
 */
public class VirtualFileCatalogueTest {

    private static int fileCount;

    public VirtualFileCatalogueTest() {
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


    private static void listAllNodes(File cloudFiles) {
        File[] list = cloudFiles.listFiles();
        for (File f : list) {
            fileCount++;
//            debug("Files: "+f.getAbsolutePath());
            if (f.isDirectory()) {
                listAllNodes(f);
            }
        }
    }
    /**
     * Test of registerNodeEntry method, of class VirtualFileCatalogue.
     */
    @Test
    public void testRegisterNodeEntry() throws Exception {
        System.out.println("registerNodeEntry");
        IResourceEntry entry = new ResourceFileEntry("entry");
        SimpleVRCatalogue instance = new SimpleVRCatalogue();
        instance.registerResourceEntry(entry);
        
        boolean exists = instance.resourceEntryExists(entry);
        assertTrue(exists);
        
        instance.unregisterResourceEntry(entry);
        
    }



    /**
     * Test of deleteNodeEntry method, of class VirtualFileCatalogue.
     * @throws MalformedURLException 
     * @throws URISyntaxException 
     */
    @Test
    public void testDeleteNodeEntry() throws MalformedURLException, URISyntaxException {
        System.out.println("deleteNodeEntry");
        IResourceEntry entry = new ResourceFolderEntry("entry");
        SimpleVRCatalogue instance = new SimpleVRCatalogue();
        instance.unregisterResourceEntry(entry);
        boolean exists = instance.resourceEntryExists(entry);
        
        assertFalse(exists);
    }


    /**
     * Test of getTopLevelEntries method, of class VirtualFileCatalogue.
     */
    @Test
    public void testGetTopLevelEntries() throws Exception {
        System.out.println("getTopLevelEntries");
        
        Util.initTestCatalouge();
        
        SimpleVRCatalogue instance = new SimpleVRCatalogue();        
        
        ArrayList<IResourceEntry> result = instance.getTopLevelResourceEntries();
        fileCount = 0;
        for(int i=0;i<Config.CLOUD_LOCATIONS_URI.length;i++){
            File f = new File(new URI(Config.CLOUD_LOCATIONS_URI[i]));
            for(File file: f.listFiles()){
            	if(!file.getName().startsWith(".")){
            		fileCount ++;		
            	}
            }
        }        
        assertEquals(fileCount, result.size());
    }
}

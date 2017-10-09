package com.Canvas;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CanvasConsoleTest extends TestCase {
	private static final Logger logger = Logger.getLogger(CanvasConsoleTest.class);
	private final static String FILE_NAME = "asciidraw.txt";
	private InputStream asciiFile = null;
	private Canvas canvas = new Canvas(22, 6);
	
	@Before
    public void setup() throws IOException {
    	Thread currentThread = Thread.currentThread();
		ClassLoader loader = currentThread.getContextClassLoader() ;
	    asciiFile = loader.getResourceAsStream(FILE_NAME) ;
    }
	
    @After
    public void teardown() throws IOException {
        if (asciiFile != null) {
        	asciiFile.close();
        }
        asciiFile = null;
    }
    
    @Test 
	  public void testFileIsValid() throws Exception {
    	setup();
    	assertTrue(asciiFile.read() > 0);
	  }
    
    interface Shape {
    	public void render();
    }
    class Line implements Shape {

		@Override
		public void render() {
			logger.info("This is a Line.");
			
		}
    }
}


package org.jbones.coreapp;

import org.jbones.core.*;
import org.jbones.dao.*;
import org.jbones.util.*;

import junit.framework.*;
/**
	Class to illustrate how to test an App that uses org.jbones.core.
*/
public class App_Test extends TestCase {

   public void testApp() {

      try {
         system.out.println("core factories:"ServiceLocator.getFactoriesToString());
      } catch(Exception e) {
         Log.getLog(Log.ERR).log(e);
      } finally {
             	
      }
   }
   public App_Test(java.lang.String testName) {
        super(testName);
   }
   public static Test suite() {
        TestSuite suite = new TestSuite(App_Test.class);
        return suite;
   }
   public static void main(String[] args){
      App_Test a = new App_Test("main");
      a.testApp();
      System.exit(0);                                     	
   }
}

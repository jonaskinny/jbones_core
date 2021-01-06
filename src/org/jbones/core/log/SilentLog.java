package org.jbones.core.log;

import java.io.PrintStream;
import java.io.FileOutputStream;

import org.jbones.core.*;
/**
Class that directs logging statement to nothing.
*/
public class SilentLog extends org.jbones.core.Log {
   
   // THIS LOG SUPPRESSES ALL LOGIN STATEMENTS.
   // INTENT IS TO USE THIS LOG IN PROD FOR VERBOSE LOGGING
   
   /**
   Prints the stackTrace of the passed exception to the standard out.
   */
	public void log(Exception e){

   }
   /**
   Prints the passed string to the standard out.
   */
	public void log(String e){
	                         	   


	}
}

package org.jbones.core.log;

import java.io.PrintStream;
import java.io.FileOutputStream;

import org.jbones.core.*;
/**
Class that directs logging statement to the System.err PrintStream.
This PrintStream is directed to a file in the caller's process
directory named 'err.log'.
*/
public class ErrLog extends org.jbones.core.Log {
   
   static {
     try{
      PrintStream printOut = 
         new PrintStream(new FileOutputStream(getAppName() + "_err.log",true));
      System.setErr(printOut);
      }catch(Exception e){
         throw new RuntimeException(CoreException.getStackTrace(e));                  	
      }              	
   }
   private static final PrintStream logStream = System.err;
   
   /**
   Prints the stackTrace of the passed exception to the standard err.
   */
	public void log(Exception e){
	  logStream.println(getDate() + " : " + getStackTrace(e));
     logStream.flush();
   }
   /**
   Prints the passed string to the standard err.
   */
	public void log(String e){
	                         	   
     logStream.println(getDate() + " : " + e);
     logStream.flush();
      
	}
}

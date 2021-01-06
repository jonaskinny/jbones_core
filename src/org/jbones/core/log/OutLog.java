package org.jbones.core.log;

import java.io.PrintStream;
import java.io.FileOutputStream;

import org.jbones.core.*;
/**
Class that directs logging statement to the System.out PrintStream.
This PrintStream is directed to a file in the caller's process
directory named 'out.log'.
*/
public class OutLog extends org.jbones.core.Log {
   
   static {
     try{
      PrintStream printOut = 
         new PrintStream(new FileOutputStream(getAppName() + "_out.log",true));
      System.setOut(printOut);
      }catch(Exception e){
         throw new RuntimeException(CoreException.getStackTrace(e));                  	
      }              	
   }
   private static final PrintStream logStream = System.out;
   
   /**
   Prints the stackTrace of the passed exception to the standard out.
   */
	public void log(Exception e){
	  logStream.println(getDate() + " : " + getStackTrace(e));
     logStream.flush();
   }
   /**
   Prints the passed string to the standard out.
   */
	public void log(String e){
	                         	   
     logStream.println(getDate() + " : " + e);
     logStream.flush();
      
	}
}

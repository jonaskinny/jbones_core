package org.jbones.core;

import java.io.*;
import java.util.*;
/** 
	Class that represents an exception originating from some core package.
*/
public class CoreException extends java.lang.Exception {

public CoreException(String s){
      super(s);
   }


public static String getStackTrace(Exception e)
   {
      StringWriter writer = new StringWriter();
      PrintWriter out = new PrintWriter(writer);
      (e).printStackTrace(out);
      out.flush();
      out.close();
      return writer.toString();
  } 
}

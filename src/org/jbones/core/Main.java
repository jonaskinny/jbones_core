package org.jbones.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;

import org.jbones.core.util.ClassName;
import org.jbones.core.io.*;

import java.io.IOException;
/**
	Main class of the jbones core package.
*/
public class Main {
   public static void main(String[] args) {
     System.out.println("running main in org.jbones.core...");
     try {
      Collection services = ServiceLocator.getServices();
      Iterator i = services.iterator();
      while (i.hasNext()) {
         System.out.println("core service:" + ClassName.name(i.next().getClass()));
      }
     } catch (Exception e) {
         System.err.println(e.getMessage());
     }
     Compare compare = new Compare();
     try
		{
			compare.getDiff(compare.dir1,compare.dir2);
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
  }
}

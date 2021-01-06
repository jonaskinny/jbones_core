package org.jbones.core.util;

import java.util.*;
import java.io.*;

import org.jbones.core.*;

public class PropertiesUtil {
   /**
      convenience function for loading a properties object
      from the classpath. The object paramater is passed in 
      from the caller to use the caller's classloader.
   **/
   public static Properties loadPropertiesFileFromClassPath(
      String path,ClassLoader classloader)
      {
         Properties objProperties = new Properties();
         InputStream objIS = null;
         try{
            objIS = classloader.getResourceAsStream(path);
            objProperties.load(objIS);
            objIS.close();
         }catch(Exception e){
            try{
               if(objIS!=null)
                  objIS.close();
            }catch(Exception e2){
               System.err.println(e2.getMessage());
               //Log.getLog(Log.ERR).log(e2);
            }
            System.err.println(e.getMessage());
            Log.getLog(Log.ERR).log(e);
         }
         return objProperties;
      }
   /**
      convenience function for loading a properties object
      from the classpath using This object's classloader.
   **/
   public static Properties loadPropertiesFileFromClassPath(String path)
   {
      Properties objProperties = new Properties();
      InputStream objIS = null;
      try{
         objIS = PropertiesUtil.class.getResourceAsStream(path);
         objProperties.load(objIS);
         objIS.close();
      }catch(Exception e) {
         try{
            if(objIS!=null)
               objIS.close();
         }catch(Exception e2){
            System.err.println(e2.getMessage());
            //Log.getLog(Log.ERR).log(e2);
         }
         System.err.println(e.getMessage());
         //Log.getLog(Log.ERR).log(e);
      }
      return objProperties;
   }
   /**
      convenience function for loading a properties object
      from the file system
   **/
   public static Properties loadPropertiesFileFromFileSystem(String path) {
      Properties p = new Properties();
      try {
         InputStream input = new FileInputStream(path);
         p.load(input);
         input.close();
      } catch (FileNotFoundException fnfe) {
         Log.getLog(Log.ERR).log(fnfe);
      } catch (IOException ioe) {
         Log.getLog(Log.ERR).log(ioe);
      } catch (IllegalArgumentException iae) {
         Log.getLog(Log.ERR).log(iae);
      }
      return p;
   }
   /**
      convenience function for writing a properties object
      to the file system
   **/
   public static void savePropertiesFiletoFileSystem(String path, Properties p) {
      try {
         OutputStream out = new FileOutputStream(path);
         p.store(out, null);
         out.close();
      } catch (FileNotFoundException fnfe) {
         Log.getLog(Log.ERR).log(fnfe);
      } catch (IOException ioe) {
         Log.getLog(Log.ERR).log(ioe);
      } catch (IllegalArgumentException iae) {
         Log.getLog(Log.ERR).log(iae);
      }
   }
}

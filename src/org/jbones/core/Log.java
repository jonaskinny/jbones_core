package org.jbones.core;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.Date;

import org.jbones.core.util.*;

/**
Class that returns Log objects.  Log objects direct logging statement 
to a designated location.
*/
public abstract class Log {
   
   public static final int ERR = 0;
   public static final int INFO = 1;
   public static final int OUT = 2;
   public static final int WARN = 3;

   private static Log sOut = null;
   private static Log sErr = null;
   private static Log sWarn = null;
   private static Log sInfo = null;
   private static Properties pLog = null;
   private static Properties pCore = null;
   
   private static String APP_NAME = "APP_NAME not initialized";

   static {

      try {
          pLog = PropertiesUtil.loadPropertiesFileFromClassPath("/org/jbones/core/log.properties");
          pCore = PropertiesUtil.loadPropertiesFileFromClassPath("/org/jbones/core/core.properties");
          setAppName(pCore.getProperty("core.appname"));
      } catch (Exception e4) {
        System.out.println("logger failed to load its resources");
      }

   }
   private static void setAppName(String appName) {
      APP_NAME = appName;
   }
   public static String getAppName() {
      return APP_NAME;
   }
   public static Log getLog(int logType){
      switch(logType){
         case Log.ERR:
            return getErrLog(pLog);
         case Log.OUT:
            return getOutLog(pLog);
         case Log.WARN:
            return getWarnLog(pLog);
         case Log.INFO:
            return getInfoLog(pLog);
         default:
            return getOutLog(pLog);
      }
   }
   private static Log getErrLog(Properties p){
      if (sErr == null){
         try{
            String class_imp = p.getProperty("log.errlog.class_imp");
            sErr = (Log)Class.forName(class_imp).newInstance();
         }catch(Exception e){
            throw new RuntimeException(CoreException.getStackTrace(e));                  	
         }
      }
      return sErr;
   }
   private static Log getOutLog(Properties p){
      if (sOut == null){
         try{
            String class_imp = p.getProperty("log.outlog.class_imp");
            sOut = (Log)Class.forName(class_imp).newInstance();
         }catch(Exception e){
            throw new RuntimeException(CoreException.getStackTrace(e));                  	
         }
      }
      return (Log)sOut;
   }
   private static Log getWarnLog(Properties p){
      if (sWarn == null){
         try{
            String class_imp = p.getProperty("log.warnlog.class_imp");
            sWarn = (Log)Class.forName(class_imp).newInstance();
         }catch(Exception e){
            throw new RuntimeException(CoreException.getStackTrace(e));                  	
         }
      }
      return (Log)sWarn;
   }
   private static Log getInfoLog(Properties p){
      if (sInfo == null){
         try{
            String class_imp = p.getProperty("log.infolog.class_imp");
            sInfo = (Log)Class.forName(class_imp).newInstance();
         }catch(Exception e){
            throw new RuntimeException(CoreException.getStackTrace(e));                  	
         }
      }
      return (Log)sInfo;
   }

  /**
  Prints the stackTrace of the passed exception to 
  * this object's printstream.
  */
	public abstract void log(Exception e);
  /**
  Prints the passed string to this object's printstream.
  */
	public abstract void log(String e);
  /**
  Returns the stackTrace of the passed exception object.
  */
	public String getStackTrace(Exception e)
   {
      StringWriter writer = new StringWriter();
      PrintWriter out = new PrintWriter(writer);
      (e).printStackTrace(out);
      out.flush();
      out.close();
      return writer.toString();
   }
   protected static String getDate(){
      return new Date(System.currentTimeMillis()).toString();                                	
   }
}

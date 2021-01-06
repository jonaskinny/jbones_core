package org.jbones.core;

import java.util.Enumeration;
import java.util.Hashtable;

/**
	Class that registers an application with the core.
*/
public abstract class App {
 /*
 This was used for the threadlocal version of ServiceLocator
   which used the appname param to assign each thread its own
   set of resources.. not needed in single app usage of ServiceLocator.
   Merely reference the ServiceLocator to initialize it.
 
 for now we only use it to prepend the appname to logging
 to see if we get a separate instance of service locator 
 based on tomcat's serving web apps from the same tomcat instance.
 just override this class returning the appname string.
 
 public void register(){
   Hashtable tl = new Hashtable();
   tl.put("appname",appName());
   ServiceLocator.setThreadLocals(tl);
 }
 */
 /*
 public void setup() throws SetupException{
   try{
      FactoryGroup fg = ServiceLocator.getFactoryGroup();
      Enumeration factories = fg.getFactories();
      while (factories.hasMoreElements()){
         try{                                   	
            ((Factory)factories.nextElement()).setup();
         }catch(Exception e){
            Log.getLog(Log.ERR).log(e);
         }
      }
   }catch(Exception e2){
    throw new SetupException(e2);                  	
   }
 }
 public void teardown() throws TeardownException {
   try{
      FactoryGroup fg = ServiceLocator.getFactoryGroup();
      Enumeration factories = fg.getFactories();
      while (factories.hasMoreElements()){
         try{
            ((Factory)factories.nextElement()).teardown();
         }catch(Exception e){
            Log.getLog(Log.ERR).log(e);                  	
         }                                   	
      }
   }catch(Exception e2){
    throw new TeardownException(e2);
   }                    	
 }
  public void addSetupListener(SetupListener arg) throws SetupException{
    throw new SetupException(new IFException());                                                               	
   }
   public void removeSetupListener(SetupListener arg) throws SetupException{
    throw new SetupException(new IFException());                                                               	
   }
   public void addTeardownListener(TeardownListener arg)
      throws TeardownException{
    throw new TeardownException(new IFException());
   }
    public void removeTeardownListener(TeardownListener arg)
      throws TeardownException{
    throw new TeardownException(new IFException());
   }
   */
  protected abstract String appName();
}

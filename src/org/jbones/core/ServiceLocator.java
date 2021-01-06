package org.jbones.core;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.EventObject;

import javax.naming.*;
import javax.naming.spi.*;

import org.jbones.core.dao.*;
/**
	Class for accessing Services that have been placed in the InitialContext
*/
public class ServiceLocator {
   private static Context context = null;
	static {
      Log.getLog(Log.OUT).log("ServiceLocator static initial method executing ...");
	   context = getInitialContext();
   }
   public static Context getInitialContext() {
      // load the InitialContext if this is the first time it has been referenced
      if (context == null) {
         Log.getLog(Log.OUT).log("context object in servicelocator is null");
         Hashtable env = new Hashtable();
         env.put(Context.INITIAL_CONTEXT_FACTORY,"org.jbones.core.CoreInitialContextFactory");
         try {
            context = new InitialContext(env);
            Log.getLog(Log.OUT).log("context object in servicelocator is initialized");
         } catch(Exception e){
            Log.getLog(Log.ERR).log("problem looking up Initial Context from : "+
            (String)env.get(Context.INITIAL_CONTEXT_FACTORY));
            throw new RuntimeException(CoreException.getStackTrace(e));
         }
     // initialize all services before they are referenced
     Collection services = null;
     try {
         services = getServices();
     } catch(Exception e2) {
       Log.getLog(Log.ERR).log("problem initializing services");
       throw new RuntimeException(CoreException.getStackTrace(e2));
     }
     Service service = null;
     Object object = null;
     String serviceName = null;
     if (services != null) {
        Iterator elements = services.iterator();
        while (elements.hasNext()) {
           object = elements.next();
           // only call initialize() on Services
           if (null != object && object instanceof Service) {
              try {
                  serviceName = ((Service)object).getName();
                  Log.getLog(Log.OUT).log("initializing service:" + serviceName);
                  ((Service)object).initialize();
              } catch(Exception e3) {
                   Log.getLog(Log.ERR).log("problem initializing service:" + serviceName);
                   throw new RuntimeException(CoreException.getStackTrace(e3));
              } finally {
                        if (! serviceIsSafe((Service)object, serviceName)) {
                           Log.getLog(Log.ERR).log("service:" + serviceName +
                           " is not safe to use, and will not be returned by ServiceLocator unless it is later deemed safe.  " +
                           "Configuration is likely the cause of the Service not being safe to use " +
                           "as Services are marked as unsafe if initialization issues arrise that would " +
                           "make the Service unsafe to use");
                        }
                 }
              }
           }
        }
     }
     return context;
   }
   public static Service getService(String serviceName) throws ServiceLocatorException {
     Log.getLog(Log.OUT).log("getting service:"+serviceName);

      try {
         Service service = (Service)context.lookup(serviceName);
         if (serviceIsSafe(service,serviceName)) {
            return service;
         }
         throw new ServiceLocatorException("service: " + serviceName + " is unsafe to use and will not be returned");
      } catch(Exception e){
       Log.getLog(Log.ERR).log("problem looking up service:"+ serviceName);
       Log.getLog(Log.ERR).log(e.getMessage());
       throw new ServiceLocatorException(e);                       	
      }
   }
   public static DAO getDAO(String DAOName) throws ServiceLocatorException {
      Log.getLog(Log.OUT).log("getting DAO:"+DAOName);
      try {
          return ((DAOFactory)getService("DAOFactory")).getDAO(DAOName);
      } catch(Exception e){
       Log.getLog(Log.ERR).log("problem looking up DAO:"+DAOName);
       Log.getLog(Log.ERR).log(e.getMessage());
       throw new ServiceLocatorException(e);                       	
      }
   }
   public static Collection getServices() throws ServiceLocatorException {
     try{
     return ((Hashtable)context.getEnvironment()).values();
      }catch(Exception e){
       Log.getLog(Log.ERR).log("problem looking up services collection");
       throw new ServiceLocatorException(e);                       	
      }
   }
   public static Enumeration getServiceNames() throws ServiceLocatorException {
     try{
     return (context.getEnvironment().keys());
      }catch(Exception e){
       Log.getLog(Log.ERR).log("problem looking up services enumeration");
       throw new ServiceLocatorException(e);                       	
      }
   }
   private static boolean serviceIsSafe(Service service, String serviceName) {
      try {
         return (null != service && service.safeToUse());
      } catch (CoreException ce) {
         Log.getLog(Log.ERR).log("problem checking safety of service:" + serviceName);
         Log.getLog(Log.ERR).log("service:" + serviceName + " must be deemed unsafe and not used");
         Log.getLog(Log.ERR).log(CoreException.getStackTrace(ce));
         return false;
      }
   }
}

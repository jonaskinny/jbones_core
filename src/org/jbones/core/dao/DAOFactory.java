package org.jbones.core.dao;

import java.util.*;

import org.jbones.core.*;
import org.jbones.core.util.*;
/**
	Class that represents a DAO Factory in the abstract sense.
*/
public abstract class DAOFactory extends Factory {
   protected static Hashtable objects = new Hashtable();
   static {
	   Properties p = new PropertiesUtil().loadPropertiesFileFromClassPath("/org/jbones/core/dao.properties");
	   StringTokenizer tokens = new StringTokenizer(p.getProperty("dao.names"), ",", false);
      String name = null;
      while(tokens.hasMoreTokens()) {
            name = tokens.nextToken();
            Log.getLog(Log.OUT).log("loading dao:" + name);

            try {
               objects.put(name,Class.forName
               (p.getProperty("dao." + name + ".class_imp")).newInstance());
            } catch (Exception e) {
               Log.getLog(Log.ERR).log("problem loading dao:"+name);
               Log.getLog(Log.ERR).log(e.getMessage());
               Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
            }
      }

   }
   public abstract DAO getDAO(String DAOName) throws CoreException;

}

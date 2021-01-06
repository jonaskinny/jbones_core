package org.jbones.core;

import javax.naming.*;
import javax.naming.spi.*;

import java.util.Hashtable;
import java.util.Properties;

import org.jbones.core.util.*;

public class CoreInitialContextFactory 
implements javax.naming.spi.InitialContextFactory {
   
   private Context context = null;
   
   public Context getInitialContext(Hashtable env) {
      if (context == null){
         //System.out.println("context factory.getInitialContext()");
         Log.getLog(Log.OUT).log("context factory.getInitialContext()");
         Properties p = new PropertiesUtil().
            loadPropertiesFileFromClassPath("/org/jbones/core/jndi.properties");
         String class_imp = p.getProperty("context.initialcontext.class_imp");
         try{
            //System.out.println("loading Context :" + class_imp);
            Log.getLog(Log.OUT).log("loading Context :" + class_imp);
            context = (Context)Class.forName(class_imp).newInstance();
         }catch(Exception e){
          //System.err.println(e);
          Log.getLog(Log.ERR).log(e);
          throw new RuntimeException(CoreException.getStackTrace(e));
         }
      }
      return context;
    }

}

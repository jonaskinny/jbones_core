package org.jbones.core;

import javax.naming.*;

import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import org.jbones.core.util.*;

class CoreInitialContext implements Context {
	private static Hashtable objects = new Hashtable();
   static {
	  Properties p = new PropertiesUtil().
            loadPropertiesFileFromClassPath("/org/jbones/core/jndi.properties");
         StringTokenizer tokens = 
      new StringTokenizer(p.getProperty("jndi.names"), ",", false);
      String name = null;
      while(tokens.hasMoreTokens())
         { 
            name = tokens.nextToken();
            Log.getLog(Log.OUT).log("loading jndiname:" + name);

            try {
               objects.put(name,Class.forName
               (p.getProperty("jndi." + name + ".class_imp")).newInstance());
            } catch (Exception e) {
               Log.getLog(Log.ERR).log("problem loading jndiname:"+name);
               Log.getLog(Log.ERR).log(e.getMessage());
               Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
            }
         }

   }
   public Object addToEnvironment(String s, Object obj) throws NamingException
   {
    return objects.put(s,obj);
   }

   public void bind(String s, Object obj) throws NamingException
   {
    objects.put(s,obj);
   }

   public void bind(Name name, Object obj) throws NamingException 
   {
    bind("" + name,obj);
   }

   public void close() throws NamingException
   {
    objects = null;
   }
   
   public String composeName(String s, String s1) throws NamingException
   {
    throw new NamingException("not supported");
   }

   public Name composeName(Name name, Name name1) throws NamingException
   {
    throw new NamingException("not supported");
   }
   
   public Context createSubcontext(String s) throws NamingException
   {
    throw new NamingException("not supported");
   }

   public Context createSubcontext(Name name) throws NamingException 
   {
    throw new NamingException("not supported");
   }

   public void destroySubcontext(String s) throws NamingException
   {
    throw new NamingException("not supported");
   }

   public void destroySubcontext(Name name) throws NamingException 
   {
    throw new NamingException("not supported");
   }

   public Hashtable getEnvironment() throws NamingException 
   {
     return objects;
   }

   public String getNameInNamespace() throws NamingException 
   {
    throw new NamingException("not supported");
   }

   public NameParser getNameParser(String s) throws NamingException 
   {
    throw new NamingException("not supported");
   }

   public NameParser getNameParser(Name name) throws NamingException
   {
    throw new NamingException("not supported");
   }

   public NamingEnumeration list(String s) throws NamingException 
   {
    throw new NamingException("not supported");
   }

   public NamingEnumeration list(Name name) throws NamingException
   {
    throw new NamingException("not supported");
   }

   public NamingEnumeration listBindings(String s) throws NamingException 
   {
    throw new NamingException("not supported");
   }

   public NamingEnumeration listBindings(Name name) throws NamingException
   {
    throw new NamingException("not supported");
   }

   public Object lookup(String s) throws NamingException
   {
    if (objects != null){
     return objects.get(s);
    }
    return null;
   }

   public Object lookup(Name name) throws NamingException 
   {
    return lookup("" + name);
   }
   
   public Object lookupLink(String s) throws NamingException
   {
    throw new NamingException("not supported");
   }

   public Object lookupLink(Name name) throws NamingException
   {
    throw new NamingException("not supported");
   }

   public void rebind(String s, Object obj) throws NamingException 
   {
    bind(s,obj);
   }
   
   public void rebind(Name name, Object obj) throws NamingException
   {
    rebind("" + name,obj);
   }

   public Object removeFromEnvironment(String s) throws NamingException
   {
    if (objects != null){
     return objects.remove(s);
    }
    return null;
   }

   public void rename(String s, String s1) throws NamingException
   {
    throw new NamingException("not supported");
   }

   public void rename(Name name, Name name1) throws NamingException
   {
    throw new NamingException("not supported");
   }

   public void unbind(String s) throws NamingException 
   {
    objects.remove(s);
   }

   public void unbind(Name name) throws NamingException 
   {
    unbind("" + name);
   }
   
}

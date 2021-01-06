package org.jbones.core.util;
/**
	Class that represents a Class's simple class name (without the package name)
*/
import java.lang.reflect.*;

public class ClassName {
 
  public static String name(Class theClass) throws NoSuchMethodException {
    String cName =  theClass.getConstructor(new Class[]{}).getName();
    Package cPackage = theClass.getPackage();
    if (cPackage != null){
      cName = cName.substring(cPackage.getName().length()+1,cName.length());
    }
    return cName;
  }
}

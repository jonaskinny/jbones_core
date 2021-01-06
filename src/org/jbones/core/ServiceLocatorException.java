package org.jbones.core;

/**
	Class that represents an exception occured during a process on an object
  that implements some interface that contains a method of this exception's name.
*/
public class ServiceLocatorException extends CoreException {
private static final long serialVersionUID=0L;
   public ServiceLocatorException(String s) {
      super(s);
   }
   
   public ServiceLocatorException(Exception e) {
      super(getStackTrace(e));
   }
}

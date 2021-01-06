package org.jbones.core;

/**
	Class that represents an exception occured during a process on an object
  that implements some interface that contains a method of this exception's name.
*/
public class SetupException extends CoreException {
private static final long serialVersionUID=0L;
   public SetupException(String s) {
      super(s);
   }
   
   public SetupException(Exception e) {
      super(getStackTrace(e));
   }
}

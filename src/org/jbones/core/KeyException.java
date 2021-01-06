package org.jbones.core;

import org.jbones.core.CoreException;

/**
	Class that represents an exception occured during a process on an object
  that implements some interface that contains a method of this exception's name.
*/
public class KeyException extends CoreException {
private static final long serialVersionUID=0L;
   public KeyException(String s) {
      super(s);
   }
   
   public KeyException(Exception e) {
      super(getStackTrace(e));
   }
}

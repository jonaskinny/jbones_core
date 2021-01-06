package org.jbones.core;

import org.jbones.core.CoreException;

/** 
	Class that represents an exception occured during a process on an object
  that implements some interface that contains a method of this exception's name.
*/
public class ObjectCopyException extends CoreException {
   private static final long serialVersionUID=0L;
   public ObjectCopyException(String s) {
      super(s);
   }
   
   public ObjectCopyException(Exception e) {
      super(getStackTrace(e));
   }
}
